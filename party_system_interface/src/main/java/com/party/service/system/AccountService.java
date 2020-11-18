package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Account;

import java.util.*;

/**
 * account业务逻辑层
 */
public interface AccountService {


    public List<Account> findAll();


    public PageResult<Account> findPage(int page, int size);


    public List<Account> findList(Map<String,Object> searchMap);


    public PageResult<Account> findPage(Map<String,Object> searchMap,int page, int size);


    public Account findById(String id);

    public void add(Account account);


    public void update(Account account);


    public void delete(String id);

}
