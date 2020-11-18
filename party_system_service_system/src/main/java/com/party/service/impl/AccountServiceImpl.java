package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.AccountMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Account;
import com.party.service.system.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Account> findAll() {
        return accountMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Account> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Account> accounts = (Page<Account>) accountMapper.selectAll();
        return new PageResult<Account>(accounts.getTotal(),accounts.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Account> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return accountMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Account> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Account> accounts = (Page<Account>) accountMapper.selectByExample(example);
        return new PageResult<Account>(accounts.getTotal(),accounts.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Account findById(String id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param account
     */
    public void add(Account account) {
        accountMapper.insert(account);
    }

    /**
     * 修改
     * @param account
     */
    public void update(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        accountMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Account.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 用户
            if(searchMap.get("username")!=null && !"".equals(searchMap.get("username"))){
                criteria.andLike("username","%"+searchMap.get("username")+"%");
            }
            // 密码
            if(searchMap.get("password")!=null && !"".equals(searchMap.get("password"))){
                criteria.andLike("password","%"+searchMap.get("password")+"%");
            }

            // 1可用0不可用
            if(searchMap.get("status")!=null ){
                criteria.andEqualTo("status",searchMap.get("status"));
            }

        }
        return example;
    }

}
