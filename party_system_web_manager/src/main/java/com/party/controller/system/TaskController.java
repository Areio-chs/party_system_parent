package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Task;
import com.party.service.system.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Reference
    private TaskService taskService;

    @GetMapping("/findAll")
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Task> findPage(int page, int size){
        return taskService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Task> findList(@RequestBody Map<String,Object> searchMap){
        return taskService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Task> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  taskService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Task findById(String id){
        return taskService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Task task){
        taskService.add(task);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Task task){
        taskService.update(task);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        taskService.delete(id);
        return new Result();
    }

}
