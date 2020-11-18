package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.System;
import com.party.service.system.SystemService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Reference
    private SystemService systemService;

    @GetMapping("/findAll")
    public List<System> findAll(){
        return systemService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<System> findPage(int page, int size){
        return systemService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<System> findList(@RequestBody Map<String,Object> searchMap){
        return systemService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<System> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  systemService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public System findById(String id){
        return systemService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody System system){
        systemService.add(system);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody System system){
        systemService.update(system);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        systemService.delete(id);
        return new Result();
    }

}
