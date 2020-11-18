package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Power;

import java.util.*;

/**
 * power业务逻辑层
 */
public interface PowerService {


    public List<Power> findAll();


    public PageResult<Power> findPage(int page, int size);


    public List<Power> findList(Map<String,Object> searchMap);


    public PageResult<Power> findPage(Map<String,Object> searchMap,int page, int size);


    public Power findById(Integer id);

    public void add(Power power);


    public void update(Power power);


    public void delete(Integer id);

}
