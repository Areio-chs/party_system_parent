package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.FileSystem;
import com.party.service.system.FileSystemService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/fileSystem")
public class FileSystemController {

    @Reference
    private FileSystemService fileSystemService;

    @GetMapping("/findAll")
    public List<FileSystem> findAll(){
        return fileSystemService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<FileSystem> findPage(int page, int size){
        return fileSystemService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<FileSystem> findList(@RequestBody Map<String,Object> searchMap){
        return fileSystemService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<FileSystem> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  fileSystemService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public FileSystem findById(String sysId){
        return fileSystemService.findById(sysId);
    }


    @PostMapping("/add")
    public Result add(@RequestBody FileSystem fileSystem){
        fileSystemService.add(fileSystem);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody FileSystem fileSystem){
        fileSystemService.update(fileSystem);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String sysId){
        fileSystemService.delete(sysId);
        return new Result();
    }

}
