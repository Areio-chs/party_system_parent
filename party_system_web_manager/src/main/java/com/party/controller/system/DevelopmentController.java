package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.Development;
import com.party.service.system.BaseUserService;
import com.party.service.system.DevelopmentService;
import com.party.vo.CommonVo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/development")
public class DevelopmentController {

    @Reference
    private DevelopmentService developmentService;

    @Reference
    private BaseUserService baseUserService;

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

    @PostMapping("/updateStatus")
    public Result updateStatus(String id, boolean status,int type){
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("userId",id);
        if (status){
            Development development = developmentService.findList(searchMap).get(0);
            switch (type){
                case 0:
                    development.setStatus(5);
                    development.setIsActivist("1");
                    development.setActivistTime(new Date());
                    developmentService.update(development);
                    break;
                case 1:
                    development.setStatus(5);
                    development.setIsDevelop("1");
                    development.setDevelopTime(new Date());
                    developmentService.update(development);
                    break;
                case 2:
                    development.setStatus(5);
                    development.setIsPre("1");
                    development.setPreMemberTime(new Date());
                    developmentService.update(development);
                    break;
                case 3:
                    development.setStatus(5);
                    development.setIsMember("1");
                    development.setMemberTime(new Date());
                    developmentService.update(development);
                    break;
            }
        }
        return new Result();
    }
}
