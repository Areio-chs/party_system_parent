package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Change;
import com.party.service.system.ChangeService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/change")
public class ChangeController {

    @Reference
    private ChangeService changeService;

    @GetMapping("/findAll")
    public List<Change> findAll(){
        return changeService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Change> findPage(int page, int size){
        return changeService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Change> findList(@RequestBody Map<String,Object> searchMap){
        return changeService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Change> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  changeService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Change findById(String id){
        return changeService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Change change){
        changeService.add(change);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Change change){
        changeService.update(change);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        changeService.delete(id);
        return new Result();
    }

}
