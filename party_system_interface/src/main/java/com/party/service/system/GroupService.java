package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Group;

import java.util.*;

/**
 * group业务逻辑层
 */
public interface GroupService {


    public List<Group> findAll();


    public PageResult<Group> findPage(int page, int size);


    public List<Group> findList(Map<String,Object> searchMap);


    public PageResult<Group> findPage(Map<String,Object> searchMap,int page, int size);


    public Group findById(String id);

    public void add(Group group);


    public void update(Group group);


    public void delete(String id);

}
