package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.*;
import com.party.entity.PageResult;
import com.party.pojo.system.*;
import com.party.service.system.BaseUserService;
import com.party.util.IdWorker;
import com.party.vo.ActivistVo;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.System;
import java.util.List;
import java.util.Map;


@Service(interfaceClass =BaseUserService.class )
public class BaseUserServiceImpl implements BaseUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private LeagueBranchMapper leagueBranchMapper;
    @Autowired
    private PartyMapper partyMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private DevelopmentMapper developmentMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<BaseUser> findAll() {
        return baseUserMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<BaseUser> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<BaseUser> baseUsers = (Page<BaseUser>) baseUserMapper.selectAll();
        return new PageResult<BaseUser>(baseUsers.getTotal(),baseUsers.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<BaseUser> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return baseUserMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<BaseUser> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<BaseUser> baseUsers = (Page<BaseUser>) baseUserMapper.selectByExample(example);
        return new PageResult<BaseUser>(baseUsers.getTotal(),baseUsers.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public BaseUser findById(String id) {
        return baseUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param activistVo
     */
    @Transactional
    public void add(ActivistVo activistVo) {
        System.out.println(activistVo.toString());
        //涉及插入发展表（成为积极分子的时间，培养人1和培养人2）和基本用户表
        BaseUser baseUser = new BaseUser();
        BeanUtils.copyProperties(activistVo,baseUser);
        baseUser.setId(idWorker.nextId()+"");
        baseUser.setTypeId(0);//积极分子的类型都是0
        baseUserMapper.insert(baseUser);
        //如果用户id在发展表里面已经存在了，那么就更新，不在就插入
        Development development = new Development();
        development.setUserId(baseUser.getId());
        development= developmentMapper.selectOne(development);
        Development development2 = new Development();
//        BeanUtils.copyProperties(activistVo,development2);因为复制的到的目前只有ActivistTime
        if (development!=null){
            development2.setId(development.getId());
            developmentMapper.updateByPrimaryKey(development2);
        }else {//新增
            //处理培养人id，前端传过来是json数组["1","","",""]
            if (activistVo.getCulture1Id()!=null && !"".equals(activistVo.getCulture1Id())){
                String cul1 = handleCultureId(activistVo.getCulture1Id());
                development2.setCulture1Id(cul1);
            }
            if (activistVo.getCulture2Id()!=null && !"".equals(activistVo.getCulture2Id())){
                String cul2 = handleCultureId(activistVo.getCulture2Id());
                development2.setCulture2Id(cul2);
            }
            development2.setActivistTime(activistVo.getActivistTime());
            development2.setId(idWorker.nextId()+"");
            development2.setIsActivist("1");
            development2.setUserId(baseUser.getId());
            developmentMapper.insert(development2);
        }
    }

    public String handleCultureId(String cultureId){
            JSONArray jsonArray = JSONArray.fromObject(cultureId);
            return  (String) jsonArray.get(jsonArray.size() - 1);
    }
    /**
     * 修改
     * @param baseUser
     */
    public void update(BaseUser baseUser) {
        baseUserMapper.updateByPrimaryKeySelective(baseUser);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        baseUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<ActivistVo> findActivist(String name, int page, int size) {
        PageHelper.startPage(page, size);
        Page<ActivistVo> activistList = (Page<ActivistVo>)baseUserMapper.findActivist(name);

        for (ActivistVo activist:activistList){
            //1.查出党总支的名字
            if (activist.getGeneralId()!=null) {
                General general = generalMapper.selectByPrimaryKey(activist.getGeneralId());
                activist.setGeneralName(general.getGeneralName());
            }
            //2.团支部名字
            if (activist.getLeagueBranchId()!=null){
            LeagueBranch leagueBranch = leagueBranchMapper.selectByPrimaryKey(activist.getLeagueBranchId());
            activist.setLeagueBranchName(leagueBranch.getName());
            }
            //3.党支部名字
            if (activist.getPartyId()!=null) {
                Party party = partyMapper.selectByPrimaryKey(activist.getPartyId());
                activist.setPartyName(party.getPartyName());
            }
            //4.党小组名字
            if (activist.getGroupId()!=null){
            Group group = groupMapper.selectByPrimaryKey(activist.getGroupId());
            activist.setGroupName(group.getGroupName());}
            //5.培养人1名字
            if (activist.getCulture1Id()!=null && !"".equals(activist.getCulture1Id())){
                Example example = new Example(BaseUser.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("id",activist.getCulture1Id());
                List<BaseUser> baseUsers = baseUserMapper.selectByExample(example);
                BaseUser baseUser = baseUsers.get(0);
                activist.setCulture1Name(baseUser.getName());
            }

            if (activist.getCulture2Id()!=null && !"".equals(activist.getCulture2Id())){
                Example example = new Example(BaseUser.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("id",activist.getCulture2Id());
                List<BaseUser> baseUsers = baseUserMapper.selectByExample(example);
                BaseUser baseUser = baseUsers.get(0);
                activist.setCulture2Name(baseUser.getName());
            }

        }
        return new PageResult<ActivistVo>(activistList.getTotal(),activistList.getResult()) ;
    }

    @Override
    public List<BaseUser> findTest(String activistName) {
        return baseUserMapper.findTest(activistName);
    }


    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(BaseUser.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 学号
            if(searchMap.get("sid")!=null && !"".equals(searchMap.get("sid"))){
                criteria.andLike("sid","%"+searchMap.get("sid")+"%");
            }
            // 姓名
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 性别
            if(searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))){
                criteria.andLike("sex","%"+searchMap.get("sex")+"%");
            }
            // 年级
            if(searchMap.get("grade")!=null && !"".equals(searchMap.get("grade"))){
                criteria.andLike("grade","%"+searchMap.get("grade")+"%");
            }
            // 班级
            if(searchMap.get("classNum")!=null && !"".equals(searchMap.get("classNum"))){
                criteria.andLike("classNum","%"+searchMap.get("classNum")+"%");
            }
            // 宿舍号
            if(searchMap.get("room")!=null && !"".equals(searchMap.get("room"))){
                criteria.andLike("room","%"+searchMap.get("room")+"%");
            }
            // 个人照片
            if(searchMap.get("iamge")!=null && !"".equals(searchMap.get("iamge"))){
                criteria.andLike("iamge","%"+searchMap.get("iamge")+"%");
            }
            // 身份证
            if(searchMap.get("idCard")!=null && !"".equals(searchMap.get("idCard"))){
                criteria.andLike("idCard","%"+searchMap.get("idCard")+"%");
            }
            // 电话号码
            if(searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))){
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
            }
            // 邮箱
            if(searchMap.get("email")!=null && !"".equals(searchMap.get("email"))){
                criteria.andLike("email","%"+searchMap.get("email")+"%");
            }
            // 家庭住址
            if(searchMap.get("address")!=null && !"".equals(searchMap.get("address"))){
                criteria.andLike("address","%"+searchMap.get("address")+"%");
            }
            // 个人身份（学生）
            if(searchMap.get("identity")!=null && !"".equals(searchMap.get("identity"))){
                criteria.andLike("identity","%"+searchMap.get("identity")+"%");
            }
            // 籍贯
            if(searchMap.get("nativePlace")!=null && !"".equals(searchMap.get("nativePlace"))){
                criteria.andLike("nativePlace","%"+searchMap.get("nativePlace")+"%");
            }
            // 户籍所在地
            if(searchMap.get("residence")!=null && !"".equals(searchMap.get("residence"))){
                criteria.andLike("residence","%"+searchMap.get("residence")+"%");
            }
            // 民族
            if(searchMap.get("nation")!=null && !"".equals(searchMap.get("nation"))){
                criteria.andLike("nation","%"+searchMap.get("nation")+"%");
            }
            // 职务（副部长）
            if(searchMap.get("duty")!=null && !"".equals(searchMap.get("duty"))){
                criteria.andLike("duty","%"+searchMap.get("duty")+"%");
            }
            // 职称
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }
            // 学历
            if(searchMap.get("aducation")!=null && !"".equals(searchMap.get("aducation"))){
                criteria.andLike("aducation","%"+searchMap.get("aducation")+"%");
            }
            // 毕业院校
            if(searchMap.get("graInstitution")!=null && !"".equals(searchMap.get("graInstitution"))){
                criteria.andLike("graInstitution","%"+searchMap.get("graInstitution")+"%");
            }
            // 奖惩信息
            if(searchMap.get("rewardPunishInfo")!=null && !"".equals(searchMap.get("rewardPunishInfo"))){
                criteria.andLike("rewardPunishInfo","%"+searchMap.get("rewardPunishInfo")+"%");
            }
            // 账户id
            if(searchMap.get("accountId")!=null && !"".equals(searchMap.get("accountId"))){
                criteria.andLike("accountId","%"+searchMap.get("accountId")+"%");
            }
            // 累计积分
            if(searchMap.get("integral")!=null && !"".equals(searchMap.get("integral"))){
                criteria.andLike("integral","%"+searchMap.get("integral")+"%");
            }
            // QQ
            if(searchMap.get("qq")!=null && !"".equals(searchMap.get("qq"))){
                criteria.andLike("qq","%"+searchMap.get("qq")+"%");
            }
            // 微信
            if(searchMap.get("wechat")!=null && !"".equals(searchMap.get("wechat"))){
                criteria.andLike("wechat","%"+searchMap.get("wechat")+"%");
            }
            // 团支部id
            if(searchMap.get("leagueBranchId")!=null && !"".equals(searchMap.get("leagueBranchId"))){
                criteria.andLike("leagueBranchId","%"+searchMap.get("leagueBranchId")+"%");
            }
            // 党总支id
            if(searchMap.get("generalId")!=null && !"".equals(searchMap.get("generalId"))){
                criteria.andLike("generalId","%"+searchMap.get("generalId")+"%");
            }
            // 党支部id
            if(searchMap.get("partyId")!=null && !"".equals(searchMap.get("partyId"))){
                criteria.andLike("partyId","%"+searchMap.get("partyId")+"%");
            }
            // 党小组id
            if(searchMap.get("groupId")!=null && !"".equals(searchMap.get("groupId"))){
                criteria.andLike("groupId","%"+searchMap.get("groupId")+"%");
            }

            // 0积极分子1发展对象2预备党员
            if(searchMap.get("typeId")!=null ){
                criteria.andEqualTo("typeId",searchMap.get("typeId"));
            }

        }
        return example;
    }

}
