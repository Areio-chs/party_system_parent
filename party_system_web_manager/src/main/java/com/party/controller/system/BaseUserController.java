package com.party.controller.system;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.StringUtil;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.BaseUser;
import com.party.service.system.BaseUserService;
import com.party.vo.Activist;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/baseUser")
public class BaseUserController {

    @Reference
    private BaseUserService baseUserService;

    @GetMapping("/findAll")
    public List<BaseUser> findAll(){
        return baseUserService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<BaseUser> findPage(int page, int size){
        return baseUserService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<BaseUser> findList(@RequestBody Map<String,Object> searchMap){
        return baseUserService.findList(searchMap);
    }

    @PostMapping("/findActivist")
    public R findActivistPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        String searchName = "";
        if(searchMap!=null){
            searchName= (String) searchMap.get("name");
        }
        PageResult<Activist> activist = baseUserService.findActivist(searchName,page,size);
        return R.ok().data("activist",activist);
    }
    @PostMapping("/findTest")
    public R findTest(@RequestBody String name){
        List<BaseUser> test = baseUserService.findTest(name);
        return R.ok().data("items",test);
    }
//    @PostMapping("/addActivist")
//    public Result addActivist(@RequestBody BaseUser baseUser){
//        baseUserService.addActivist(baseUser);
//        return new Result();
//    }

    @PostMapping("/findPage")
    public PageResult<BaseUser> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  baseUserService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public BaseUser findById(String id){
        return baseUserService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody BaseUser baseUser){
        baseUserService.add(baseUser);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody BaseUser baseUser){
        baseUserService.update(baseUser);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        baseUserService.delete(id);
        return new Result();
    }

}
