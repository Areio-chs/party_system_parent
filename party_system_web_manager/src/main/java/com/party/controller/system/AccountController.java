package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Account;
import com.party.service.system.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Reference
    private AccountService accountService;

    @GetMapping("/findAll")
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Account> findPage(int page, int size){
        return accountService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Account> findList(@RequestBody Map<String,Object> searchMap){
        return accountService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Account> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  accountService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Account findById(String id){
        return accountService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Account account){
        accountService.add(account);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Account account){
        accountService.update(account);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        accountService.delete(id);
        return new Result();
    }

}
