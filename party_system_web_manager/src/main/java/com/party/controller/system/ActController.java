package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Act;
import com.party.service.system.ActService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/act")
public class ActController {

    @Reference
    private ActService actService;

    @GetMapping("/findAll")
    public List<Act> findAll(){
        return actService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Act> findPage(int page, int size){
        return actService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Act> findList(@RequestBody Map<String,Object> searchMap){
        return actService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Act> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  actService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Act findById(String id){
        return actService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Act act){
        actService.add(act);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Act act){
        actService.update(act);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        actService.delete(id);
        return new Result();
    }

}
