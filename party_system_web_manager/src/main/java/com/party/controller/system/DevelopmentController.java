package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Development;
import com.party.service.system.DevelopmentService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/development")
public class DevelopmentController {

    @Reference
    private DevelopmentService developmentService;

    @GetMapping("/findAll")
    public List<Development> findAll(){
        return developmentService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Development> findPage(int page, int size){
        return developmentService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Development> findList(@RequestBody Map<String,Object> searchMap){
        return developmentService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Development> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  developmentService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Development findById(String id){
        return developmentService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Development development){
        developmentService.add(development);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Development development){
        developmentService.update(development);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        developmentService.delete(id);
        return new Result();
    }

}
