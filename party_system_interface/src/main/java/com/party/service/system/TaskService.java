package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Task;

import java.util.*;

/**
 * task业务逻辑层
 */
public interface TaskService {


    public List<Task> findAll();


    public PageResult<Task> findPage(int page, int size);


    public List<Task> findList(Map<String,Object> searchMap);


    public PageResult<Task> findPage(Map<String,Object> searchMap,int page, int size);


    public Task findById(String id);

    public void add(Task task);


    public void update(Task task);


    public void delete(String id);

}
