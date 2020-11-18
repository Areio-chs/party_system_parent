package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Change;

import java.util.*;

/**
 * change业务逻辑层
 */
public interface ChangeService {


    public List<Change> findAll();


    public PageResult<Change> findPage(int page, int size);


    public List<Change> findList(Map<String,Object> searchMap);


    public PageResult<Change> findPage(Map<String,Object> searchMap,int page, int size);


    public Change findById(String id);

    public void add(Change change);


    public void update(Change change);


    public void delete(String id);

}
