package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.entity.Result;
import com.party.pojo.system.Group;
import com.party.pojo.system.LeagueBranch;
import com.party.pojo.system.Party;
import com.party.service.system.LeagueBranchService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/leagueBranch")
public class LeagueBranchController {

    @Reference
    private LeagueBranchService leagueBranchService;

    @GetMapping("/findAll")
    public R findAll(){
        List<LeagueBranch> list = leagueBranchService.findAll();
        return R.ok().data("items",list);
    }
    @GetMapping("/findByPartyId")
    public R findByPartyId(String groupId){
        List<LeagueBranch> list = leagueBranchService.findByGroupId(groupId);
        return R.ok().data("items",list);
    }

    @GetMapping("/findPage")
    public PageResult<LeagueBranch> findPage(int page, int size){
        return leagueBranchService.findPage(page, size);
    }



    @PostMapping("/findList")
    public List<LeagueBranch> findList(@RequestBody Map<String,Object> searchMap){
        return leagueBranchService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<LeagueBranch> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  leagueBranchService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public R findById(String id){
        LeagueBranch leagueBranch = leagueBranchService.findById(id);
        return R.ok().data("items",leagueBranch);
    }


    @PostMapping("/add")
    public R add(@RequestBody LeagueBranch leagueBranch){

        leagueBranch.setPartyId("1");
        leagueBranch.setGroupId("1");
        leagueBranchService.add(leagueBranch);
        return R.ok();
    }

    @PostMapping("/update")
    public R update(@RequestBody LeagueBranch leagueBranch){
        leagueBranch.setPartyId("1");
        leagueBranch.setGroupId("1");
        leagueBranchService.update(leagueBranch);
        return R.ok();
    }

    @GetMapping("/delete")
    public R delete(String id){
        leagueBranchService.delete(id);
        return R.ok();
    }

    @PostMapping("/findLeagueBranch")
    public R findPartyPage(@RequestBody Map<String,Object> searchMap, int page, int size){
        PageResult<LeagueBranch> leagueBranch = leagueBranchService.findPage(searchMap,page,size);
        return R.ok().data("leagueBranch",leagueBranch);
    }

    @PostMapping("/transfer")
    public R transfer(@RequestBody Map<String,Object> formLabelAlign){
        leagueBranchService.transfer(formLabelAlign);
        return R.ok();
    }
}
