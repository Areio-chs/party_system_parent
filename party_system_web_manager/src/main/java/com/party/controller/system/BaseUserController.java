package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.pojo.system.BaseUser;
import com.party.service.system.BaseUserService;
import com.party.vo.ActivistVo;
import com.party.vo.CommonVo;
import com.party.vo.DevelopmentVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/baseUser")
public class BaseUserController {

    @Reference
    private BaseUserService baseUserService;

//    @GetMapping("/findAll")
//    public List<BaseUser> findAll(){
//        return baseUserService.findAll();
//    }

    @GetMapping("/findPage")
    public PageResult<BaseUser> findPage(int page, int size){
        return baseUserService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<BaseUser> findList(@RequestBody Map<String,Object> searchMap){
        return baseUserService.findList(searchMap);
    }

    @PostMapping("/findCommonPage")
    public R findActivistPage(@RequestBody Map<String,Object> searchMap,int page, int size,int type){
//        String searchName = "";
//        if(searchMap!=null){
//            searchName= (String) searchMap.get("name");
//        }
        PageResult<CommonVo> pageInfo = baseUserService.findCommonPage(searchMap,page,size,type);

        return R.ok().data("pageInfo",pageInfo);
    }

    @GetMapping("/findCommonById")
    public R findCommonById(String id,int type){
        System.out.println("11111111111111111111");
        System.out.println(id+":"+type);

        CommonVo memberVo = baseUserService.findCommonById(id,type);
        System.out.println(memberVo.toString());
        return R.ok().data("items",memberVo);
    }

    @PostMapping("/add")
    public R add(@RequestBody CommonVo commonVo,int type){
        baseUserService.addCommon(commonVo,type);
        return R.ok();
    }
    @GetMapping("/uniqueSid")
    public R uniqueSid(String sid){
        int result = baseUserService.uniqueSid(sid);//0说明里面现在没这个学号
        return R.ok().data("items",result);
    }
    //积极/发展/预备/正式
    @PostMapping("/updateCommon")
    public R update(@RequestBody CommonVo commonVo,int type) throws Exception{
        baseUserService.updateCommon(commonVo,type);
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

    @PostMapping("/changeCul")
    public R changeCul(@RequestBody Map<String,Object> dynamicValidateForm){
        baseUserService.changeCul(dynamicValidateForm);
        return R.ok();
    }
    //---------------------------------发展对象----------------------------------------

}
