package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.Study;
import com.party.service.system.StudyService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/study")
public class StudyController {

    @Reference
    private StudyService studyService;

    @GetMapping("/findAll")
    public List<Study> findAll(){
        return studyService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Study> findPage(int page, int size){
        return studyService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Study> findList(@RequestBody Map<String,Object> searchMap){
        return studyService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public R findPage(@RequestBody Map<String,Object> searchMap, int page, int size){
        PageResult<Study> pageInfo = studyService.findPage(searchMap,page,size);
        return R.ok().data("pageInfo",pageInfo);
    }

    @GetMapping("/findById")
    public R findById(String id){
        Study study = studyService.findById(id);
        return R.ok().data("items",study);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Study study){
        studyService.add(study);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Study study){
        studyService.update(study);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        studyService.delete(id);
        return new Result();
    }

}
