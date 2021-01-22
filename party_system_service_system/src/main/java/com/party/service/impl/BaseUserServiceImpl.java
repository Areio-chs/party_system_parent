package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.BaseUserMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseUser;
import com.party.service.system.BaseUserService;
import com.party.vo.Activist;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;

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
     * @param baseUser
     */
    public void add(BaseUser baseUser) {
        baseUserMapper.insert(baseUser);
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
    public List<Activist> findActivist() {
        return baseUserMapper.findActivist();
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
