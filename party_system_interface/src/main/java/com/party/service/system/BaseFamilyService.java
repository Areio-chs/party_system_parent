package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseFamily;

import java.util.*;

/**
 * baseFamily业务逻辑层
 */
public interface BaseFamilyService {


    public List<BaseFamily> findAll();


    public PageResult<BaseFamily> findPage(int page, int size);


    public List<BaseFamily> findList(Map<String,Object> searchMap);


    public PageResult<BaseFamily> findPage(Map<String,Object> searchMap,int page, int size);


    public BaseFamily findById(String id);

    public void add(BaseFamily baseFamily);


    public void update(BaseFamily baseFamily);


    public void delete(String id);

}
