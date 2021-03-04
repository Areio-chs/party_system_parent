package com.party.controller.excel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.party.entity.R;
import com.party.excel.ActivistData;
import com.party.excel.DevelopmentData;
import com.party.excel.MemberData;
import com.party.excel.PreMemberData;
import com.party.service.system.BaseUserService;
import com.party.vo.ActivistVo;
import com.party.vo.CommonVo;
import com.party.vo.DevelopmentVo;
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
            CommonVo commonVo = new CommonVo();
            BeanUtils.copyProperties(activistData,commonVo);
            baseUserService.excelAdd(commonVo);
        }
        return R.ok();
    }

    @PostMapping("/leadInDevelopment")
    public R leadInDevelopment(MultipartFile file){
        //不知道为啥file文件传不到service去。
        System.out.println("导入方法执行------------------------------------------------");
        List<DevelopmentData> list = new ArrayList<>();
        try{
            list = EasyExcel.read(file.getInputStream()).head(DevelopmentData.class)
                    .sheet().doReadSync();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (DevelopmentData developmentData:list){
            CommonVo commonVo = new CommonVo();
            BeanUtils.copyProperties(developmentData,commonVo);
            baseUserService.excelAddDevelopment(commonVo);
        }
        return R.ok();
    }
    //导入预备党员
    @PostMapping("/leadInPreMember")
    public R leadInPreMember(MultipartFile file){
        //不知道为啥file文件传不到service去。
        System.out.println("导入方法执行------------------------------------------------");
        List<PreMemberData> list = new ArrayList<>();
        try{
            list = EasyExcel.read(file.getInputStream()).head(PreMemberData.class)
                    .sheet().doReadSync();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (PreMemberData preMemberData:list){
            CommonVo commonVo = new CommonVo();
            BeanUtils.copyProperties(preMemberData,commonVo);
            baseUserService.excelAddPreMember(commonVo);
        }
        return R.ok();
    }
    //导入正式党员
    @PostMapping("/leadInMember")
    public R leadInMember(MultipartFile file){
        //不知道为啥file文件传不到service去。
        List<MemberData> list = new ArrayList<>();
        try{
            list = EasyExcel.read(file.getInputStream()).head(MemberData.class)
                    .sheet().doReadSync();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (MemberData memberData:list){
            CommonVo commonVo = new CommonVo();
            BeanUtils.copyProperties(memberData,commonVo);
            baseUserService.excelAddMember(commonVo);
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
    @GetMapping("/leadOutDevelopment")
    public void leadOutDevelopment(HttpServletResponse response){
        //1.获取所有数据列表
        List<DevelopmentData> DevelopmentOut = baseUserService.findDevelopmentOut();
        //配置下载属性
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName="";
        try {
            fileName = URLEncoder.encode("发展对象表", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(),DevelopmentData.class).sheet("发展对象列表").doWrite(DevelopmentOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/leadOutPreMember")
    public void leadOutPreMember(HttpServletResponse response){
        //1.获取所有数据列表
        List<PreMemberData> PreMemberOut = baseUserService.findPreMemberOut();
        //配置下载属性
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName="";
        try {
            fileName = URLEncoder.encode("预备党员表", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(),PreMemberData.class).sheet("预备党员列表").doWrite(PreMemberOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/leadOutMember")
    public void leadOutMember(HttpServletResponse response){
        //1.获取所有数据列表
        List<MemberData> memberOut = baseUserService.findMemberOut();
        //配置下载属性
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName="";
        try {
            fileName = URLEncoder.encode("正式党员表", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(),MemberData.class).sheet("正式党员列表").doWrite(memberOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
