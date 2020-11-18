package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseResume;

import java.util.*;

/**
 * baseResume业务逻辑层
 */
public interface BaseResumeService {


    public List<BaseResume> findAll();


    public PageResult<BaseResume> findPage(int page, int size);


    public List<BaseResume> findList(Map<String,Object> searchMap);


    public PageResult<BaseResume> findPage(Map<String,Object> searchMap,int page, int size);


    public BaseResume findById(String id);

    public void add(BaseResume baseResume);


    public void update(BaseResume baseResume);


    public void delete(String id);

}
