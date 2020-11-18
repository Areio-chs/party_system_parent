package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Fund;

import java.util.*;

/**
 * fund业务逻辑层
 */
public interface FundService {


    public List<Fund> findAll();


    public PageResult<Fund> findPage(int page, int size);


    public List<Fund> findList(Map<String,Object> searchMap);


    public PageResult<Fund> findPage(Map<String,Object> searchMap,int page, int size);


    public Fund findById(String id);

    public void add(Fund fund);


    public void update(Fund fund);


    public void delete(String id);

}
