package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Act;

import java.util.*;

/**
 * act业务逻辑层
 */
public interface ActService {


    public List<Act> findAll();


    public PageResult<Act> findPage(int page, int size);


    public List<Act> findList(Map<String,Object> searchMap);


    public PageResult<Act> findPage(Map<String,Object> searchMap,int page, int size);


    public Act findById(String id);

    public void add(Act act);


    public void update(Act act);


    public void delete(String id);

}
