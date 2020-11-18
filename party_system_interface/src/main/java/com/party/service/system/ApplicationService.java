package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Application;

import java.util.*;

/**
 * application业务逻辑层
 */
public interface ApplicationService {


    public List<Application> findAll();


    public PageResult<Application> findPage(int page, int size);


    public List<Application> findList(Map<String,Object> searchMap);


    public PageResult<Application> findPage(Map<String,Object> searchMap,int page, int size);


    public Application findById(String id);

    public void add(Application application);


    public void update(Application application);


    public void delete(String id);

}
