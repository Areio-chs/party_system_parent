package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.ActDetailMapper;
import com.party.dao.ActMapper;
import com.party.dao.BaseUserMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Act;
import com.party.pojo.system.ActDetail;
import com.party.pojo.system.BaseUser;
import com.party.service.system.ActService;
import com.party.service.system.StudyService;
import com.party.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = ActService.class )
public class ActServiceImpl implements ActService {

    @Autowired
    private ActMapper actMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private ActDetailMapper actDetailMapper;
    @Autowired
    private IdWorker idWorker;
    /**
     * 返回全部记录
     * @return
     */
    public List<Act> findAll() {
        return actMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Act> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Act> acts = (Page<Act>) actMapper.selectAll();
        return new PageResult<Act>(acts.getTotal(),acts.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Act> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return actMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Act> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Act> acts = (Page<Act>) actMapper.selectByExample(example);
        return new PageResult<Act>(acts.getTotal(),acts.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Act findById(String id) {
        return actMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param act
     */
    @Transactional
    public void add(Act act) {
        System.out.println(act.toString());
        List<String> peopleList = act.getOrganization();
        act.setId(idWorker.nextId()+"");
        act.setStatus("0");
        act.setJoinNum(4);
        actMapper.insert(act);
        for(int i=0;i<peopleList.size();i++){
            ActDetail actDetail = new ActDetail();
            actDetail.setId(idWorker.nextId()+"");
            String s = peopleList.get(i);
            String[] split = s.split(",");
            actDetail.setUserId(split[3].substring(1,20));

            //查出这个人的类型
            BaseUser baseUser=new BaseUser();
            baseUser.setId(split[3].substring(1,20));
            BaseUser baseUser1 = baseUserMapper.selectOne(baseUser);
            Integer typeId = baseUser1.getTypeId();
            String typeName="";
            if (typeId==0){
                typeName="积极分子";
            }
            if (typeId==1){
                typeName="发展对象";
            }
            if (typeId==2){
                typeName="预备党员";
            }
            if (typeId==3){
                typeName="党员";
            }
            actDetail.setTypeName(typeName);
            actDetail.setName(baseUser1.getName());
            actDetail.setActId(act.getId());
            actDetail.setStatus("0");

            actDetailMapper.insert(actDetail);
        }
    }

    /**
     * 修改
     * @param act
     */
    public void update(Act act) {
        actMapper.updateByPrimaryKeySelective(act);
    }

    /**
     *  删除
     * @param id
     */
    @Transactional
    public void delete(String id) {
        ActDetail actDetail = new ActDetail();
        actDetail.setActId(id);
        List<ActDetail> actDetailList = actDetailMapper.select(actDetail);
        for (ActDetail actDetail1:actDetailList) {
            actDetailMapper.deleteByPrimaryKey(actDetail1);
        }

        actMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Act.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 活动名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 活动备注
            if(searchMap.get("note")!=null && !"".equals(searchMap.get("note"))){
                criteria.andLike("note","%"+searchMap.get("note")+"%");
            }
            // 活动地点
            if(searchMap.get("address")!=null && !"".equals(searchMap.get("address"))){
                criteria.andLike("address","%"+searchMap.get("address")+"%");
            }
            // 发布人
            if(searchMap.get("publisher")!=null && !"".equals(searchMap.get("publisher"))){
                criteria.andLike("publisher","%"+searchMap.get("publisher")+"%");
            }
            // 活动类型的名称
            if(searchMap.get("typeName")!=null && !"".equals(searchMap.get("typeName"))){
                criteria.andLike("typeName","%"+searchMap.get("typeName")+"%");
            }
            // 活动文件
            if(searchMap.get("taskId")!=null && !"".equals(searchMap.get("taskId"))){
                criteria.andLike("taskId","%"+searchMap.get("taskId")+"%");
            }

            // 活动类型id
            if(searchMap.get("typeId")!=null ){
                criteria.andEqualTo("typeId",searchMap.get("typeId"));
            }
            // 参与人数
            if(searchMap.get("joinNum")!=null ){
                criteria.andEqualTo("joinNum",searchMap.get("joinNum"));
            }

        }
        example.setOrderByClause("time desc");
        return example;
    }

}
