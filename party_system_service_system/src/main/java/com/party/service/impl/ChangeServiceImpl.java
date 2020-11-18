package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.ChangeMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Change;
import com.party.service.system.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ChangeServiceImpl implements ChangeService {

    @Autowired
    private ChangeMapper changeMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Change> findAll() {
        return changeMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Change> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Change> changes = (Page<Change>) changeMapper.selectAll();
        return new PageResult<Change>(changes.getTotal(),changes.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Change> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return changeMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Change> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Change> changes = (Page<Change>) changeMapper.selectByExample(example);
        return new PageResult<Change>(changes.getTotal(),changes.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Change findById(String id) {
        return changeMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param change
     */
    public void add(Change change) {
        changeMapper.insert(change);
    }

    /**
     * 修改
     * @param change
     */
    public void update(Change change) {
        changeMapper.updateByPrimaryKeySelective(change);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        changeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Change.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 转出支部
            if(searchMap.get("fromName")!=null && !"".equals(searchMap.get("fromName"))){
                criteria.andLike("fromName","%"+searchMap.get("fromName")+"%");
            }
            // 转入支部
            if(searchMap.get("toName")!=null && !"".equals(searchMap.get("toName"))){
                criteria.andLike("toName","%"+searchMap.get("toName")+"%");
            }
            // 审核人
            if(searchMap.get("auditor")!=null && !"".equals(searchMap.get("auditor"))){
                criteria.andLike("auditor","%"+searchMap.get("auditor")+"%");
            }
            // 审核状态
            if(searchMap.get("status")!=null && !"".equals(searchMap.get("status"))){
                criteria.andLike("status","%"+searchMap.get("status")+"%");
            }
            // 外键用户id
            if(searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))){
                criteria.andLike("userId","%"+searchMap.get("userId")+"%");
            }


        }
        return example;
    }

}
