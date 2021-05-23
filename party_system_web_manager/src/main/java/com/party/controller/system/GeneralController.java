package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.pojo.system.General;
import com.party.service.system.GeneralService;
import com.party.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
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
    public R findById(String id){
        General general = generalService.findById(id);
        return  R.ok().data("items",general);
    }


    @PostMapping("/add")
    public R add(@RequestBody General general){
        generalService.add(general);
        return R.ok();
    }

    @PostMapping("/update")
    public R update(@RequestBody General general){
        generalService.update(general);
        return R.ok();
    }

    @GetMapping("/delete")
    public R delete(String id){
        generalService.delete(id);
        return R.ok();
    }

    @PostMapping("/findGeneral")
    public R findGeneralPage(@RequestBody Map<String,Object> searchMap, int page, int size){
        PageResult<General> general = generalService.findPage(searchMap,page,size);
        return R.ok().data("general",general);
    }

    @PostMapping("/transfer")
    public R transfer(@RequestBody Map<String,Object> formLabelAlign){
        generalService.transfer(formLabelAlign);
        return R.ok();
    }

}
