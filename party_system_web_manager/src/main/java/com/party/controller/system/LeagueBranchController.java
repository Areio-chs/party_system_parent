package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.LeagueBranch;
import com.party.service.system.LeagueBranchService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/leagueBranch")
public class LeagueBranchController {

    @Reference
    private LeagueBranchService leagueBranchService;

    @GetMapping("/findAll")
    public List<LeagueBranch> findAll(){
        return leagueBranchService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<LeagueBranch> findPage(int page, int size){
        return leagueBranchService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<LeagueBranch> findList(@RequestBody Map<String,Object> searchMap){
        return leagueBranchService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<LeagueBranch> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  leagueBranchService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public LeagueBranch findById(String id){
        return leagueBranchService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody LeagueBranch leagueBranch){
        leagueBranchService.add(leagueBranch);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody LeagueBranch leagueBranch){
        leagueBranchService.update(leagueBranch);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        leagueBranchService.delete(id);
        return new Result();
    }

}
