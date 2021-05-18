package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.pojo.system.Study;
import com.party.pojo.system.StudyDetail;
import com.party.service.system.StudyDetailService;
import com.party.service.system.StudyService;
import com.party.vo.CommonVo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/studyDetail")
public class StudyDetailController {
    @Reference
    private StudyDetailService studyDetailService;

    @GetMapping("/updateFinish")
    public R updateStatus(String type,String myStatus){
        System.out.println("进入后端");
        studyDetailService.updateStatus(type,myStatus);
        return R.ok();
    }
    @PostMapping("/findMyPage")
    public R findPage(@RequestBody Map<String,Object> searchMap,int page, int size,String type){
//        if(searchMap==null)
//        {
//            System.out.println("~~~~");
//        }
//        System.out.println(page);
//        System.out.println(size);
//        System.out.println(type);
//        String pageInfo="";
        PageResult<StudyDetail> pageInfo=studyDetailService.findPage(searchMap,page, size, type);
        return R.ok().data("pageInfo",pageInfo);
    }

}
