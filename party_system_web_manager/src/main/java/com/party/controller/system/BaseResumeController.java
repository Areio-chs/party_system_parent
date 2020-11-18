package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.BaseResume;
import com.party.service.system.BaseResumeService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/baseResume")
public class BaseResumeController {

    @Reference
    private BaseResumeService baseResumeService;

    @GetMapping("/findAll")
    public List<BaseResume> findAll(){
        return baseResumeService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<BaseResume> findPage(int page, int size){
        return baseResumeService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<BaseResume> findList(@RequestBody Map<String,Object> searchMap){
        return baseResumeService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<BaseResume> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  baseResumeService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public BaseResume findById(String id){
        return baseResumeService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody BaseResume baseResume){
        baseResumeService.add(baseResume);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody BaseResume baseResume){
        baseResumeService.update(baseResume);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        baseResumeService.delete(id);
        return new Result();
    }

}
