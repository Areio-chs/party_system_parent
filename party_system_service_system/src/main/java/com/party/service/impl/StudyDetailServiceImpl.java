package com.party.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.StudyDetailMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Study;
import com.party.pojo.system.StudyDetail;
import com.party.service.system.StudyDetailService;
import com.party.service.system.StudyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
@Service
public class StudyDetailServiceImpl implements StudyDetailService {
    @Autowired
    private StudyDetailMapper studyDetailMapper;
    @Override
    public PageResult<StudyDetail> findPage(@RequestBody Map<String,Object> searchMap, int page, int size, String type) {
        System.out.println("here");
        PageHelper.startPage(page,size);
        Example example = createExample(type);
        Page<StudyDetail> studys = (Page<StudyDetail>) studyDetailMapper.selectByExample(example);
        System.out.println(studys);
        return new PageResult<StudyDetail>(studys.getTotal(),studys.getResult());
    }

    @Override
    public void updateStatus(String type,String myStatus) {
        StudyDetail studyDetail= new StudyDetail();
        studyDetail.setId(type);
        studyDetail.setStatus(myStatus);
        studyDetailMapper.updateByPrimaryKeySelective(studyDetail);

    }

    private Example createExample(String type){
        Example example=new Example(StudyDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studyId",type);
        return example;
    }

}
