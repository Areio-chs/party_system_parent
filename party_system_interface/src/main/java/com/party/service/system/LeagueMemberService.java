package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.LeagueMember;

import java.util.*;

/**
 * leagueMember业务逻辑层
 */
public interface LeagueMemberService {


    public List<LeagueMember> findAll();


    public PageResult<LeagueMember> findPage(int page, int size);


    public List<LeagueMember> findList(Map<String,Object> searchMap);


    public PageResult<LeagueMember> findPage(Map<String,Object> searchMap,int page, int size);


    public LeagueMember findById(String id);

    public void add(LeagueMember leagueMember);


    public void update(LeagueMember leagueMember);


    public void delete(String id);

}
