package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.R;
import com.party.pojo.system.ActDetail;
import com.party.pojo.system.StudyDetail;
import com.party.service.system.ActDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/actDetail")
public class ActDetailController {

    @Reference
    private ActDetailService actDetailService;
    @PostMapping("/findMyPage")
    public R findPage(@RequestBody Map<String,Object> searchMap, int page, int size, String type){
        PageResult<ActDetail> pageInfo=actDetailService.findPage(searchMap,page, size, type);
        return R.ok().data("pageInfo",pageInfo);
    }
}
