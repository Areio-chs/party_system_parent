package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.General;

import java.util.*;

/**
 * general业务逻辑层
 */
public interface GeneralService {


    public List<General> findAll();


    public PageResult<General> findPage(int page, int size);


    public List<General> findList(Map<String,Object> searchMap);


    public PageResult<General> findPage(Map<String,Object> searchMap,int page, int size);


    public General findById(String id);

    public void add(General general);


    public void update(General general);


    public void delete(String id);


}
