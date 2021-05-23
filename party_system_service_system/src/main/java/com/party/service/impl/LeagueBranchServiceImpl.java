package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.GroupMapper;
import com.party.dao.LeagueBranchMapper;
import com.party.dao.PartyMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Group;
import com.party.pojo.system.LeagueBranch;
import com.party.pojo.system.Party;
import com.party.service.system.LeagueBranchService;
import com.party.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LeagueBranchServiceImpl implements LeagueBranchService {

    @Autowired
    private LeagueBranchMapper leagueBranchMapper;
    @Autowired
    private PartyMapper partyMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private IdWorker idWorker;

    /**
     * 返回全部记录
     * @return
     */
    public List<LeagueBranch> findAll() {
        return leagueBranchMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<LeagueBranch> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<LeagueBranch> leagueBranchs = (Page<LeagueBranch>) leagueBranchMapper.selectAll();
        return new PageResult<LeagueBranch>(leagueBranchs.getTotal(),leagueBranchs.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<LeagueBranch> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return leagueBranchMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<LeagueBranch> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        List<LeagueBranch> leagueBranchList = leagueBranchMapper.selectByExample(example);
        for (LeagueBranch leagueBranch : leagueBranchList) {
            if (!(leagueBranch.getGroupId()==null)){
                Group group = groupMapper.selectByPrimaryKey(leagueBranch.getGroupId());
                leagueBranch.setGroupName(group.getGroupName());
            }
            if (!(leagueBranch.getPartyId()==null)){
                Party party = partyMapper.selectByPrimaryKey(leagueBranch.getPartyId());
                leagueBranch.setPartyName(party.getPartyName());
            }
        }
        Page<LeagueBranch> leagueBranchs = (Page<LeagueBranch>) leagueBranchList;
        return new PageResult<LeagueBranch>(leagueBranchs.getTotal(),leagueBranchs.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public LeagueBranch findById(String id) {
        LeagueBranch leagueBranch = leagueBranchMapper.selectByPrimaryKey(id);
        if (!(leagueBranch.getGroupId()==null)){
            Group group = groupMapper.selectByPrimaryKey(leagueBranch.getGroupId());
            leagueBranch.setGroupName(group.getGroupName());
        }
        if (!(leagueBranch.getPartyId()==null)){
            Party party = partyMapper.selectByPrimaryKey(leagueBranch.getPartyId());
            leagueBranch.setPartyName(party.getPartyName());
        }
        return leagueBranch;
    }

    /**
     * 新增
     * @param leagueBranch
     */
    public void add(LeagueBranch leagueBranch) {
        leagueBranch.setId(idWorker.nextId()+"");
        leagueBranch.setCreateTime(new Date());
        leagueBranchMapper.insert(leagueBranch);
    }

    /**
     * 修改
     * @param leagueBranch
     */
    public void update(LeagueBranch leagueBranch) {
        leagueBranchMapper.updateByPrimaryKeySelective(leagueBranch);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        leagueBranchMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void transfer(Map<String, Object> formLabelAlign) {

    }

    @Override
    public List<LeagueBranch> findByGroupId(String groupId) {
        LeagueBranch leagueBranch = new LeagueBranch();
        leagueBranch.setGroupId(groupId);
        return  leagueBranchMapper.select(leagueBranch);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(LeagueBranch.class);
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
            // phone
            if(searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))){
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
            }
            // address
            if(searchMap.get("address")!=null && !"".equals(searchMap.get("address"))){
                criteria.andLike("address","%"+searchMap.get("address")+"%");
            }
            // note
            if(searchMap.get("note")!=null && !"".equals(searchMap.get("note"))){
                criteria.andLike("note","%"+searchMap.get("note")+"%");
            }
            // group_id
            if(searchMap.get("groupId")!=null && !"".equals(searchMap.get("groupId"))){
                criteria.andLike("groupId","%"+searchMap.get("groupId")+"%");
            }
            // account_id
            if(searchMap.get("accountId")!=null && !"".equals(searchMap.get("accountId"))){
                criteria.andLike("accountId","%"+searchMap.get("accountId")+"%");
            }
            // party_id
            if(searchMap.get("partyId")!=null && !"".equals(searchMap.get("partyId"))){
                criteria.andLike("partyId","%"+searchMap.get("partyId")+"%");
            }
            // 党员id,在base_user中
            if(searchMap.get("memberId")!=null && !"".equals(searchMap.get("memberId"))){
                criteria.andLike("memberId","%"+searchMap.get("memberId")+"%");
            }


        }
        return example;
    }

}
