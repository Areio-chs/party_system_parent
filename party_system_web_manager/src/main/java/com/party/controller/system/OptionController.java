package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.R;
import com.party.service.system.OptionService;
import com.party.vo.OptionVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/option")
public class OptionController {

    @Reference
    private OptionService optionService;
//课程分类列表（树形）
    @GetMapping("/getOption")
    public R getOption() {
        //list集合泛型是一级分类
        List<OptionVo> optionList = optionService.getOption();
        return R.ok().data("items",optionList);
    }

    @GetMapping("/getTransferOption")
    public R getTransferOption() {
        //list集合泛型是一级分类
        List<OptionVo> optionList = optionService.getTransferOption();
        return R.ok().data("items",optionList);
    }
}
