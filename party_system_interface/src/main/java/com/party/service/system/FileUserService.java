package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.FileUser;

import java.util.*;

/**
 * fileUser业务逻辑层
 */
public interface FileUserService {


    public List<FileUser> findAll();


    public PageResult<FileUser> findPage(int page, int size);


    public List<FileUser> findList(Map<String,Object> searchMap);


    public PageResult<FileUser> findPage(Map<String,Object> searchMap,int page, int size);


    public FileUser findById(String id);

    public void add(FileUser fileUser);


    public void update(FileUser fileUser);


    public void delete(String id);

}
