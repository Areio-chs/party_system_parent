package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.BaseFamily;
import com.party.service.system.BaseFamilyService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/baseFamily")
public class BaseFamilyController {

    @Reference
    private BaseFamilyService baseFamilyService;

    @GetMapping("/findAll")
    public List<BaseFamily> findAll(){
        return baseFamilyService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<BaseFamily> findPage(int page, int size){
        return baseFamilyService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<BaseFamily> findList(@RequestBody Map<String,Object> searchMap){
        return baseFamilyService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<BaseFamily> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  baseFamilyService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public BaseFamily findById(String id){
        return baseFamilyService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody BaseFamily baseFamily){
        baseFamilyService.add(baseFamily);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody BaseFamily baseFamily){
        baseFamilyService.update(baseFamily);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        baseFamilyService.delete(id);
        return new Result();
    }

}
