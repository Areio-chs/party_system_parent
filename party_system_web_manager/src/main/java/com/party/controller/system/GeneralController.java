package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.General;
import com.party.service.system.GeneralService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/general")
public class GeneralController {

    @Reference
    private GeneralService generalService;

    @GetMapping("/findAll")
    public R findAll(){
        List<General> list = generalService.findAll();
        return R.ok().data("items",list);
    }

    @GetMapping("/findPage")
    public PageResult<General> findPage(int page, int size){
        return generalService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<General> findList(@RequestBody Map<String,Object> searchMap){
        return generalService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<General> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  generalService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public General findById(String id){
        return generalService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody General general){
        generalService.add(general);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody General general){
        generalService.update(general);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        generalService.delete(id);
        return new Result();
    }

}
