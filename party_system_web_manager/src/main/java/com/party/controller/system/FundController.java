package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Fund;
import com.party.service.system.FundService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/fund")
public class FundController {

    @Reference
    private FundService fundService;

    @GetMapping("/findAll")
    public List<Fund> findAll(){
        return fundService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Fund> findPage(int page, int size){
        return fundService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Fund> findList(@RequestBody Map<String,Object> searchMap){
        return fundService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Fund> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  fundService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Fund findById(String id){
        return fundService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Fund fund){
        fundService.add(fund);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Fund fund){
        fundService.update(fund);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        fundService.delete(id);
        return new Result();
    }

}
