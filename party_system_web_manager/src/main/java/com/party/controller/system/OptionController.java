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
    //培养联系人列表
    @GetMapping("/getOption")
    public R getOption() {
        //list集合泛型是一级分类
        List<OptionVo> optionList = optionService.getOption();
        return R.ok().data("items",optionList);
    }
    //党小组下所有人员列表
    @GetMapping("/getPeopleOption")
    public R getPeopleOption() {
        List<OptionVo> optionList = optionService.getPeopleOption();
        return R.ok().data("items",optionList);
    }
    //党小组列表
    @GetMapping("/getTransferOption")
    public R getTransferOption() {
        //list集合泛型是一级分类
        List<OptionVo> optionList = optionService.getTransferOption();
        return R.ok().data("items",optionList);
    }
}
