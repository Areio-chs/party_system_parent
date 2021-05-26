package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.Group;
import com.party.pojo.system.Party;
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
    @GetMapping("/findByPartyId")
    public R findByPartyId(String partyId){
        System.out.println("---------------------------");
        System.out.println(partyId);
        List<Group> list = groupService.findByPartyId(partyId);
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
    public R findById(String id){
        Group group = groupService.findById(id);
        return R.ok().data("items",group);
    }


    @PostMapping("/add")
    public R add(@RequestBody Group group){
        System.out.println(group.getPartyId());
        group.setPartyId("1");
        System.out.println(group.getPartyId());
        groupService.add(group);
        return R.ok();
    }

    @PostMapping("/update")
    public R update(@RequestBody Group group){
        group.setPartyId("1");
        groupService.update(group);
        return R.ok();
    }

    @GetMapping("/delete")
    public R delete(String id){
        groupService.delete(id);
        return R.ok();
    }

    @PostMapping("/findGroup")
    public R findGroupPage(@RequestBody Map<String,Object> searchMap, int page, int size){
        PageResult<Group> group = groupService.findPage(searchMap,page,size);
        return R.ok().data("group",group);
    }

    @PostMapping("/transfer")
    public R transfer(@RequestBody Map<String,Object> formLabelAlign){
        groupService.transfer(formLabelAlign);
        return R.ok();
    }

}
