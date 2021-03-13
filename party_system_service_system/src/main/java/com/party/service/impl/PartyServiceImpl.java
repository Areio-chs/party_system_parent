package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.GeneralMapper;
import com.party.dao.GroupMapper;
import com.party.dao.PartyMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Group;
import com.party.pojo.system.General;
import com.party.pojo.system.Party;
import com.party.service.system.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private PartyMapper partyMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Party> findAll() {
        return partyMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Party> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Party> partys = (Page<Party>) partyMapper.selectAll();
        return new PageResult<Party>(partys.getTotal(),partys.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Party> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return partyMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Party> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        List<Party> partyList = partyMapper.selectByExample(example);
        for (Party party : partyList) {
            if (!(party.getGeneralId()==null)){
                General general = generalMapper.selectByPrimaryKey(party.getGeneralId());
                party.setGeneralName(general.getGeneralName());
            }
        }
        Page<Party> partys = (Page<Party>) partyList;
        return new PageResult<Party>(partys.getTotal(),partys.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Party findById(String id) {
        Party party = partyMapper.selectByPrimaryKey(id);
        if (!(party.getGeneralId()==null)){
            General general = generalMapper.selectByPrimaryKey(party.getGeneralId());
            party.setGeneralName(general.getGeneralName());
        }
        return party;
    }

    /**
     * 新增
     * @param party
     */
    public void add(Party party) {

        partyMapper.insert(party);
    }

    /**
     * 修改
     * @param party
     */
    public void update(Party party) {

        partyMapper.updateByPrimaryKeySelective(party);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {

        partyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Party> findByGeneralId(String generalId) {
        Party party = new Party();
        party.setGeneralId(generalId);
        List<Party> partyList = partyMapper.select(party);
        return partyList;
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Party.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 主键id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 党支部名字
            if(searchMap.get("partyName")!=null && !"".equals(searchMap.get("partyName"))){
                criteria.andLike("partyName","%"+searchMap.get("partyName")+"%");
            }
            // 党支部电话
            if(searchMap.get("partyPhone")!=null && !"".equals(searchMap.get("partyPhone"))){
                criteria.andLike("partyPhone","%"+searchMap.get("partyPhone")+"%");
            }
            // 党支部地址
            if(searchMap.get("partyAddress")!=null && !"".equals(searchMap.get("partyAddress"))){
                criteria.andLike("partyAddress","%"+searchMap.get("partyAddress")+"%");
            }
            // 备注
            if(searchMap.get("partyNote")!=null && !"".equals(searchMap.get("partyNote"))){
                criteria.andLike("partyNote","%"+searchMap.get("partyNote")+"%");
            }
            // 党总支id
            if(searchMap.get("generalId")!=null && !"".equals(searchMap.get("generalId"))){
                criteria.andLike("generalId","%"+searchMap.get("generalId")+"%");
            }
            // 账户id
            if(searchMap.get("accountId")!=null && !"".equals(searchMap.get("accountId"))){
                criteria.andLike("accountId","%"+searchMap.get("accountId")+"%");
            }


        }
        return example;
    }

}
