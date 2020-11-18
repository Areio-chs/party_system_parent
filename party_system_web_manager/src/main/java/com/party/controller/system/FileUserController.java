package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.FileUser;
import com.party.service.system.FileUserService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/fileUser")
public class FileUserController {

    @Reference
    private FileUserService fileUserService;

    @GetMapping("/findAll")
    public List<FileUser> findAll(){
        return fileUserService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<FileUser> findPage(int page, int size){
        return fileUserService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<FileUser> findList(@RequestBody Map<String,Object> searchMap){
        return fileUserService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<FileUser> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  fileUserService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public FileUser findById(String id){
        return fileUserService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody FileUser fileUser){
        fileUserService.add(fileUser);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody FileUser fileUser){
        fileUserService.update(fileUser);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        fileUserService.delete(id);
        return new Result();
    }

}
