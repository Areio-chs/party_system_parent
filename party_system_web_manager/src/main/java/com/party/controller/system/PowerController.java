package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Power;
import com.party.service.system.PowerService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/power")
public class PowerController {

    @Reference
    private PowerService powerService;

    @GetMapping("/findAll")
    public List<Power> findAll(){
        return powerService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Power> findPage(int page, int size){
        return powerService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Power> findList(@RequestBody Map<String,Object> searchMap){
        return powerService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Power> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  powerService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Power findById(Integer id){
        return powerService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Power power){
        powerService.add(power);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Power power){
        powerService.update(power);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        powerService.delete(id);
        return new Result();
    }

}
