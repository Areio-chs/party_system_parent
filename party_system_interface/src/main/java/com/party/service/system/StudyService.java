package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Study;

import java.util.*;

/**
 * study业务逻辑层
 */
public interface StudyService {


    public List<Study> findAll();


    public PageResult<Study> findPage(int page, int size);


    public List<Study> findList(Map<String,Object> searchMap);


    public PageResult<Study> findPage(Map<String,Object> searchMap,int page, int size);


    public Study findById(String id);

    public void add(Study study);


    public void update(Study study);


    public void delete(String id);

}
