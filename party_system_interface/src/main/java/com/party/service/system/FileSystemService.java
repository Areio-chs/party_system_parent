package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.FileSystem;

import java.util.*;

/**
 * fileSystem业务逻辑层
 */
public interface FileSystemService {


    public List<FileSystem> findAll();


    public PageResult<FileSystem> findPage(int page, int size);


    public List<FileSystem> findList(Map<String,Object> searchMap);


    public PageResult<FileSystem> findPage(Map<String,Object> searchMap,int page, int size);


    public FileSystem findById(String sys_id);

    public void add(FileSystem fileSystem);


    public void update(FileSystem fileSystem);


    public void delete(String sys_id);

}
