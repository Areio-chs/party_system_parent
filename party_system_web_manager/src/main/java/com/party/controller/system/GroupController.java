package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.Group;
import com.party.service.system.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Reference
    private GroupService groupService;

    @GetMapping("/findAll")
    public R findAll(){
        List<Group> list = groupService.findAll();
        return R.ok().data("items",list);
    }

    @GetMapping("/findPage")
    public PageResult<Group> findPage(int page, int size){
        return groupService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Group> findList(@RequestBody Map<String,Object> searchMap){
        return groupService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Group> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  groupService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Group findById(String id){
        return groupService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Group group){
        groupService.add(group);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Group group){
        groupService.update(group);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        groupService.delete(id);
        return new Result();
    }

}
