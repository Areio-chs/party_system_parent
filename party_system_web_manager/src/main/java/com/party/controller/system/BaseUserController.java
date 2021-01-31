package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.support.Parameter;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.BaseUser;
import com.party.service.system.BaseUserService;
import com.party.vo.ActivistVo;
import org.springframework.util.StringUtils;
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
        PageResult<ActivistVo> activist = baseUserService.findActivist(searchName,page,size);

        return R.ok().data("activist",activist);
    }
    @PostMapping("/findTest")
    public R findTest(@RequestBody String name){
        List<BaseUser> test = baseUserService.findTest(name);
        return R.ok().data("items",test);
    }

    @PostMapping("/findPage")
    public PageResult<BaseUser> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  baseUserService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public R findById(String id){
//        ActivistVo activistVo = baseUserService.findById(id);
//        return R.ok().data("items",activistVo);
        ActivistVo activistVo = baseUserService.findById(id);
        List<String> cul1List = new ArrayList<>();
        List<String> cul2List = new ArrayList<>();
        if (!StringUtils.isEmpty(activistVo.getCulture1Id())){
            cul1List = baseUserService.handleCul(activistVo.getCulture1Id());
        }
        if (!StringUtils.isEmpty(activistVo.getCulture2Id())){
            cul2List = baseUserService.handleCul(activistVo.getCulture2Id());
        }

        return R.ok().data("items",activistVo).data("cul1",cul1List).data("cul2",cul2List);
    }


    @PostMapping("/add")
    public R add(@RequestBody ActivistVo activistVo){
        baseUserService.add(activistVo);
        return R.ok();
    }

    @PostMapping("/update")
    public R update(@RequestBody ActivistVo activistVo){

        baseUserService.update(activistVo);
        return R.ok();
    }

    @GetMapping("/delete")
    public R delete(String id){
        baseUserService.delete(id);
        return R.ok();
    }

    @PostMapping("/transfer")
    public R transfer(@RequestBody Map<String,Object> formLabelAlign){
        baseUserService.transfer(formLabelAlign);
        return R.ok();
    }
}
