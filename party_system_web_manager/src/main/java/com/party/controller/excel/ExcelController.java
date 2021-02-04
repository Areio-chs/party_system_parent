package com.party.controller.excel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.party.entity.R;
import com.party.excel.ActivistData;
import com.party.service.system.BaseUserService;
import com.party.vo.ActivistVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Reference
    private BaseUserService baseUserService;

    @PostMapping("/leadInActivist")
    public R leadIn(MultipartFile file){
        //不知道为啥file文件传不到service去。
        List<ActivistData> list = new ArrayList<>();
        try{
            list = EasyExcel.read(file.getInputStream()).head(ActivistData.class)
                    .sheet().doReadSync();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (ActivistData activistData:list){
            ActivistVo activistVo = new ActivistVo();
            BeanUtils.copyProperties(activistData,activistVo);
            baseUserService.excelAdd(activistVo);
        }
        return R.ok();
    }

    @GetMapping("/leadOutActivist")
    public void leadOut(HttpServletResponse response){
        //1.获取所有数据列表
        List<ActivistData> activistOut = baseUserService.findActivistOut();
        //配置下载属性
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName="";
        try {
            fileName = URLEncoder.encode("积极分子表", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(),ActivistData.class).sheet("积极分子列表").doWrite(activistOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
