package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseUser;
import com.party.vo.ActivistVo;

import java.util.*;

/**
 * baseUser业务逻辑层
 */
public interface BaseUserService {


    public List<BaseUser> findAll();


    public PageResult<BaseUser> findPage(int page, int size);


    public List<BaseUser> findList(Map<String,Object> searchMap);


    public PageResult<BaseUser> findPage(Map<String,Object> searchMap,int page, int size);


    public BaseUser findById(String id);

    public void add(ActivistVo activistVo);


    public void update(BaseUser baseUser);


    public void delete(String id);

//    void addActivist(BaseUser baseUser);

    public PageResult<ActivistVo>  findActivist(String name, int page, int size);

    public List<BaseUser> findTest(String activistName);
}
