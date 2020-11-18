package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.LeagueMemberMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.LeagueMember;
import com.party.service.system.LeagueMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class LeagueMemberServiceImpl implements LeagueMemberService {

    @Autowired
    private LeagueMemberMapper leagueMemberMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<LeagueMember> findAll() {
        return leagueMemberMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<LeagueMember> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<LeagueMember> leagueMembers = (Page<LeagueMember>) leagueMemberMapper.selectAll();
        return new PageResult<LeagueMember>(leagueMembers.getTotal(),leagueMembers.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<LeagueMember> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return leagueMemberMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<LeagueMember> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<LeagueMember> leagueMembers = (Page<LeagueMember>) leagueMemberMapper.selectByExample(example);
        return new PageResult<LeagueMember>(leagueMembers.getTotal(),leagueMembers.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public LeagueMember findById(String id) {
        return leagueMemberMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param leagueMember
     */
    public void add(LeagueMember leagueMember) {
        leagueMemberMapper.insert(leagueMember);
    }

    /**
     * 修改
     * @param leagueMember
     */
    public void update(LeagueMember leagueMember) {
        leagueMemberMapper.updateByPrimaryKeySelective(leagueMember);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        leagueMemberMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(LeagueMember.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // name
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // sex
            if(searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))){
                criteria.andLike("sex","%"+searchMap.get("sex")+"%");
            }
            // 民族
            if(searchMap.get("nation")!=null && !"".equals(searchMap.get("nation"))){
                criteria.andLike("nation","%"+searchMap.get("nation")+"%");
            }
            // 籍贯
            if(searchMap.get("nativePlace")!=null && !"".equals(searchMap.get("nativePlace"))){
                criteria.andLike("nativePlace","%"+searchMap.get("nativePlace")+"%");
            }
            // 身份证
            if(searchMap.get("idCard")!=null && !"".equals(searchMap.get("idCard"))){
                criteria.andLike("idCard","%"+searchMap.get("idCard")+"%");
            }
            // 手机
            if(searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))){
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
            }
            // 学历
            if(searchMap.get("aducation")!=null && !"".equals(searchMap.get("aducation"))){
                criteria.andLike("aducation","%"+searchMap.get("aducation")+"%");
            }
            // 职务
            if(searchMap.get("duty")!=null && !"".equals(searchMap.get("duty"))){
                criteria.andLike("duty","%"+searchMap.get("duty")+"%");
            }
            // 班级
            if(searchMap.get("classNum")!=null && !"".equals(searchMap.get("classNum"))){
                criteria.andLike("classNum","%"+searchMap.get("classNum")+"%");
            }
            // 团支部id
            if(searchMap.get("leagueBranchId")!=null && !"".equals(searchMap.get("leagueBranchId"))){
                criteria.andLike("leagueBranchId","%"+searchMap.get("leagueBranchId")+"%");
            }
            // account_id
            if(searchMap.get("accountId")!=null && !"".equals(searchMap.get("accountId"))){
                criteria.andLike("accountId","%"+searchMap.get("accountId")+"%");
            }
            // email
            if(searchMap.get("email")!=null && !"".equals(searchMap.get("email"))){
                criteria.andLike("email","%"+searchMap.get("email")+"%");
            }


        }
        return example;
    }

}
