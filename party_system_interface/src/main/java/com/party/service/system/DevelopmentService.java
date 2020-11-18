package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Development;

import java.util.*;

/**
 * development业务逻辑层
 */
public interface DevelopmentService {


    public List<Development> findAll();


    public PageResult<Development> findPage(int page, int size);


    public List<Development> findList(Map<String,Object> searchMap);


    public PageResult<Development> findPage(Map<String,Object> searchMap,int page, int size);


    public Development findById(String id);

    public void add(Development development);


    public void update(Development development);


    public void delete(String id);

}
