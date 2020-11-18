package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.LeagueBranch;

import java.util.*;

/**
 * leagueBranch业务逻辑层
 */
public interface LeagueBranchService {


    public List<LeagueBranch> findAll();


    public PageResult<LeagueBranch> findPage(int page, int size);


    public List<LeagueBranch> findList(Map<String,Object> searchMap);


    public PageResult<LeagueBranch> findPage(Map<String,Object> searchMap,int page, int size);


    public LeagueBranch findById(String id);

    public void add(LeagueBranch leagueBranch);


    public void update(LeagueBranch leagueBranch);


    public void delete(String id);

}
