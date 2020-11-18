package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.SystemMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.System;
import com.party.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemMapper systemMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<System> findAll() {
        return systemMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<System> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<System> systems = (Page<System>) systemMapper.selectAll();
        return new PageResult<System>(systems.getTotal(),systems.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<System> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return systemMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<System> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<System> systems = (Page<System>) systemMapper.selectByExample(example);
        return new PageResult<System>(systems.getTotal(),systems.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public System findById(String id) {
        return systemMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param system
     */
    public void add(System system) {
        systemMapper.insert(system);
    }

    /**
     * 修改
     * @param system
     */
    public void update(System system) {
        systemMapper.updateByPrimaryKeySelective(system);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        systemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(System.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }

            // 身份id 1是申请人2是积极分子
            if(searchMap.get("identityId")!=null ){
                criteria.andEqualTo("identityId",searchMap.get("identityId"));
            }

        }
        return example;
    }

}
