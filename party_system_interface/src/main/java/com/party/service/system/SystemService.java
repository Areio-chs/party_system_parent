package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.System;

import java.util.*;

/**
 * system业务逻辑层
 */
public interface SystemService {


    public List<System> findAll();


    public PageResult<System> findPage(int page, int size);


    public List<System> findList(Map<String,Object> searchMap);


    public PageResult<System> findPage(Map<String,Object> searchMap,int page, int size);


    public System findById(String id);

    public void add(System system);


    public void update(System system);


    public void delete(String id);

}
