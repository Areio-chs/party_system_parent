package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Application;
import com.party.service.system.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Reference
    private ApplicationService applicationService;

    @GetMapping("/findAll")
    public List<Application> findAll(){
        return applicationService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Application> findPage(int page, int size){
        return applicationService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Application> findList(@RequestBody Map<String,Object> searchMap){
        return applicationService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Application> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  applicationService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Application findById(String id){
        return applicationService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Application application){
        applicationService.add(application);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Application application){
        applicationService.update(application);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        applicationService.delete(id);
        return new Result();
    }

}
