package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.LeagueMember;
import com.party.service.system.LeagueMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/leagueMember")
public class LeagueMemberController {

    @Reference
    private LeagueMemberService leagueMemberService;

    @GetMapping("/findAll")
    public List<LeagueMember> findAll(){
        return leagueMemberService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<LeagueMember> findPage(int page, int size){
        return leagueMemberService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<LeagueMember> findList(@RequestBody Map<String,Object> searchMap){
        return leagueMemberService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<LeagueMember> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  leagueMemberService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public LeagueMember findById(String id){
        return leagueMemberService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody LeagueMember leagueMember){
        leagueMemberService.add(leagueMember);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody LeagueMember leagueMember){
        leagueMemberService.update(leagueMember);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        leagueMemberService.delete(id);
        return new Result();
    }

}
