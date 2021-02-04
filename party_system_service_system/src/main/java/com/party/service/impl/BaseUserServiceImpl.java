package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.controller.PartyException;
import com.party.dao.*;
import com.party.entity.PageResult;
import com.party.excel.ActivistData;
import com.party.pojo.system.*;
import com.party.service.system.BaseUserService;
import com.party.util.IdWorker;
import com.party.vo.ActivistVo;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.System;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;


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
    @Autowired
    private ChangeMapper changeMapper;

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
    public ActivistVo findById(String id) {
        //1.涉及多个表，建议用sql查询
//        ActivistVo activistVo = new ActivistVo();
//        BaseUser baseUser = new BaseUser();
//        baseUser.setId(id);
//        baseUser = baseUserMapper.selectByPrimaryKey(baseUser);
//        BeanUtils.copyProperties(baseUser,activistVo);//查出来复制到vo
//        Example example = new Example(Development.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("userId",id);
//        Development development = developmentMapper.selectOneByExample(example);
//        BeanUtils.copyProperties(development,activistVo);
        ActivistVo activistVo = baseUserMapper.findById(id);
        return activistVo;
    }

    public List<String> handleCul(String cultureId){
        BaseUser baseUser=new BaseUser();
        baseUser.setId(cultureId);
        baseUser = baseUserMapper.selectByPrimaryKey(baseUser);//查出培养人的基本信息
        List<String> cul=new ArrayList<>();
        cul.add(baseUser.getGeneralId()) ;
        cul.add(baseUser.getPartyId());
        cul.add(baseUser.getGroupId());
        cul.add(cultureId);

        return cul;

    }
    //拼接成["1","1","1"]
    public String all(BaseUser baseUser,String cultureId){
        List<String> cul=new ArrayList<>();
        cul.add(baseUser.getGeneralId()) ;
        cul.add(baseUser.getPartyId());
        cul.add(baseUser.getGroupId());
        cul.add(cultureId);
        String json = JSON.toJSONString(cul);
        return json;
    }

    /**
     * 新增
     * @param activistVo
     */
    @Transactional
    public void add(ActivistVo activistVo) {
        System.out.println(activistVo.toString());
        //查党总支的名字
        if (StringUtils.isNotEmpty(activistVo.getGeneralId())) {
            General general = generalMapper.selectByPrimaryKey(activistVo.getGeneralId());
            activistVo.setGeneralName(general.getGeneralName());
        }
        //查党支部名字
        if (StringUtils.isNotEmpty(activistVo.getPartyId())) {
            Party party = partyMapper.selectByPrimaryKey(activistVo.getPartyId());
            activistVo.setPartyName(party.getPartyName());
        }
        if (StringUtils.isNotEmpty(activistVo.getGroupId())) {
            Group group = groupMapper.selectByPrimaryKey(activistVo.getGroupId());
            activistVo.setGroupName(group.getGroupName());
        }
        if (StringUtils.isNotEmpty(activistVo.getLeagueBranchId())){
            LeagueBranch leagueBranch = leagueBranchMapper.selectByPrimaryKey(activistVo.getLeagueBranchId());
            activistVo.setLeagueBranchName(leagueBranch.getName());
        }
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
            developmentMapper.updateByPrimaryKeySelective(development2);
        }else {//新增

            if (activistVo.getCulture1Id()!=null && !"".equals(activistVo.getCulture1Id())){
                String cul1 = handleCultureId(activistVo.getCulture1Id());
                development2.setCulture1Id(cul1);
                //培养人1的名称
                development2.setCulture1Name(baseUserMapper.selectByPrimaryKey(cul1).getName());
                development2.setCulture1Sid(baseUserMapper.selectByPrimaryKey(cul1).getSid());
            }
            if (activistVo.getCulture2Id()!=null && !"".equals(activistVo.getCulture2Id())){
                String cul2 = handleCultureId(activistVo.getCulture2Id());
                development2.setCulture2Id(cul2);
                development2.setCulture2Name(baseUserMapper.selectByPrimaryKey(cul2).getName());
                development2.setCulture2Sid(baseUserMapper.selectByPrimaryKey(cul2).getSid());
            }
            development2.setActivistTime(activistVo.getActivistTime());
            development2.setId(idWorker.nextId()+"");
            development2.setIsActivist("1");
            development2.setUserId(baseUser.getId());
            developmentMapper.insert(development2);
        }
    }
    @Transactional
    public void excelAdd(ActivistVo activistVo) {
        System.out.println(activistVo.toString());
        //根据名字查党总支id
        Example example1 = new Example(General.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("generalName",activistVo.getGeneralName());
        General general = generalMapper.selectOneByExample(example1);
        if (general==null) {
            throw new PartyException(20001,"该党支部不存在");
        }else {
            activistVo.setGeneralId(general.getId());
        }
        //党总支
        Example example2 = new Example(Party.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("partyName",activistVo.getPartyName());
        Party party = partyMapper.selectOneByExample(example2);
        if (party==null) {
            throw new PartyException(20001,"该党总支不存在");
        }else {
            activistVo.setPartyId(party.getId());
        }
        //党小组
        Example example3 = new Example(Group.class);
        Example.Criteria criteria3 = example3.createCriteria();
        criteria3.andEqualTo("groupName",activistVo.getGroupName());
        Group group = groupMapper.selectOneByExample(example3);
        if (party==null) {
            throw new PartyException(20001,"该党小组不存在");
        }else {
            activistVo.setGroupId(group.getId());
        }
        //团支部
        Example example4 = new Example(LeagueBranch.class);
        Example.Criteria criteria4 = example4.createCriteria();
        criteria4.andEqualTo("name",activistVo.getLeagueBranchName());
        LeagueBranch leagueBranch = leagueBranchMapper.selectOneByExample(example4);
        if (leagueBranch==null){
            throw new PartyException(20001,"该团支部不存在");
        }else {
            activistVo.setLeagueBranchId(leagueBranch.getId());
        }
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
            developmentMapper.updateByPrimaryKeySelective(development2);
        }else {//新增
            if (StringUtils.isNotEmpty(activistVo.getCulture1Sid())){
                //根据学号查出这个培养人的id
                Example example= new Example(BaseUser.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("sid",activistVo.getCulture1Sid());
                BaseUser baseUser1 = baseUserMapper.selectOneByExample(example);
                development2.setCulture1Id(baseUser1.getId());
                development2.setCulture1Name(baseUser1.getName());
            }
            if (StringUtils.isNotEmpty(activistVo.getCulture2Sid())){
                //查出这个培养人的id
                Example example= new Example(BaseUser.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("sid",activistVo.getCulture2Sid());
                BaseUser baseUser1 = baseUserMapper.selectOneByExample(example);
                development2.setCulture2Id(baseUser1.getId());
                development2.setCulture2Name(baseUser1.getName());
            }

            development2.setActivistTime(activistVo.getActivistTime());
            development2.setId(idWorker.nextId()+"");
            development2.setIsActivist("1");
            development2.setUserId(baseUser.getId());
            developmentMapper.insert(development2);
        }
    }
    //判断学号是否唯一
    @Override
    public int uniqueSid(String sid) {
        BaseUser baseUser = new BaseUser();
        baseUser.setSid(sid);
        BaseUser baseUser1 = baseUserMapper.selectOne(baseUser);
        if (StringUtils.isEmpty(baseUser1.getId())){
            return 0;
        }
        return 1;
    }

    //处理培养人id，前端传过来是json数组["1","","",""]
    public String handleCultureId(String cultureId){
            JSONArray jsonArray = JSONArray.fromObject(cultureId);
            return  (String) jsonArray.get(jsonArray.size() - 1);
    }
    /**
     * 修改
     * @param activistVo
     */
    @Transactional
    public void update(ActivistVo activistVo) {
        //查党总支的名字
        if (StringUtils.isNotEmpty(activistVo.getGeneralId())) {
            General general = generalMapper.selectByPrimaryKey(activistVo.getGeneralId());
            activistVo.setGeneralName(general.getGeneralName());
        }
        //查党支部名字
        if (StringUtils.isNotEmpty(activistVo.getPartyId())) {
            Party party = partyMapper.selectByPrimaryKey(activistVo.getPartyId());
            activistVo.setPartyName(party.getPartyName());
        }
        if (StringUtils.isNotEmpty(activistVo.getGroupId())) {
            Group group = groupMapper.selectByPrimaryKey(activistVo.getGroupId());
            activistVo.setGroupName(group.getGroupName());
        }
        if (StringUtils.isNotEmpty(activistVo.getLeagueBranchId())){
            LeagueBranch leagueBranch = leagueBranchMapper.selectByPrimaryKey(activistVo.getLeagueBranchId());
            activistVo.setLeagueBranchName(leagueBranch.getName());
        }
        System.out.println("修改成员长什么样？"+activistVo.toString());
        //修改baseuser表
        BaseUser baseUser = new BaseUser();
        BeanUtils.copyProperties(activistVo,baseUser);
        baseUserMapper.updateByPrimaryKeySelective(baseUser);
        //修改development
        Development development =new Development();
        development.setUserId(activistVo.getId());
        Development development1 = developmentMapper.selectOne(development);
        development1.setActivistTime(activistVo.getActivistTime());
        if (activistVo.getCulture1Id()!="[]" && !"[]".equals(activistVo.getCulture1Id())){
            String cul1 = handleCultureId(activistVo.getCulture1Id());
            development1.setCulture1Id(cul1);
            development1.setCulture1Name(baseUserMapper.selectByPrimaryKey(cul1).getName());
        }else {
            development1.setCulture1Id(null);
        }
        if (activistVo.getCulture2Id()!="[]" && !"[]".equals(activistVo.getCulture2Id())){
            String cul2 = handleCultureId(activistVo.getCulture2Id());
            development1.setCulture2Id(cul2);
            development1.setCulture2Name(baseUserMapper.selectByPrimaryKey(cul2).getName());
        }else {
            development1.setCulture2Id(null);
        }
        developmentMapper.updateByPrimaryKey(development1);
    }

    /**
     *  删除
     * @param id
     */
    @Transactional
    public void delete(String id) {
        //先删development再删除baseuser里面的，
        Development development = new Development();
        development.setUserId(id);
        development = developmentMapper.selectOne(development);
        developmentMapper.deleteByPrimaryKey(development);
        baseUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 返回全部记录
     * @return
     */
    public List<ActivistData> findActivistOut() {
        return baseUserMapper.findActivistOut();
    }

    @Override
    public PageResult<ActivistVo> findActivist(String name, int page, int size) {
        PageHelper.startPage(page, size);
        Page<ActivistVo> activistList = (Page<ActivistVo>)baseUserMapper.findActivist(name);
        return new PageResult<ActivistVo>(activistList.getTotal(),activistList.getResult()) ;
    }

    @Override
    public List<BaseUser> findTest(String activistName) {
        return baseUserMapper.findTest(activistName);
    }

    //关系转移
    @Transactional
    @Override
    public void transfer(Map<String,Object> formLabelAlign) {
        //如果传回来的有三个id，说明是转移到另外一个小组，而不是党支部
        //如果需要审核?这里应该不马上改变了，可以新增一条，状态设置为0，审核后，给审核的那条设置状态1，然后删掉旧的。
        List<String> orgs = (List<String>) formLabelAlign.get("organization");
        String generalId = orgs.get(orgs.size()-3);
        String partyId = orgs.get(orgs.size()-2);
        String groupId = orgs.get(orgs.size()-1);
        Party newParty = partyMapper.selectByPrimaryKey(partyId);
        List<String> ids = (List<String>) formLabelAlign.get("select");//选中的所有人的id
        BaseUser baseUser = new BaseUser();
        for (String id:ids){//id是baseuser的主键
            //先查出这个人原本的党支部
            BaseUser baseUser1 = baseUserMapper.selectByPrimaryKey(id);
            Party oldParty = partyMapper.selectByPrimaryKey(baseUser1.getPartyId());

            //每个人的党小组进行改变
            baseUser.setId(id);
            baseUser.setGeneralId(generalId);
            baseUser.setGeneralName(generalMapper.selectByPrimaryKey(generalId).getGeneralName());
            baseUser.setPartyId(partyId);
            baseUser.setPartyName(partyMapper.selectByPrimaryKey(partyId).getPartyName());
            baseUser.setGroupId(groupId);
            baseUser.setGroupName(groupMapper.selectByPrimaryKey(groupId).getGroupName());
            baseUserMapper.updateByPrimaryKeySelective(baseUser);

            //向change表插入数据
            Change change = new Change();
            change.setId(idWorker.nextId()+"");
            change.setFromName(oldParty.getPartyName());
            change.setFromTime(new Date());
            change.setToName(newParty.getPartyName());
            change.setToTime(new Date());
            change.setUserId(id);
            changeMapper.insert(change);

        }

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
