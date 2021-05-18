package com.party.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.ActDetailMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Act;
import com.party.pojo.system.ActDetail;
import com.party.pojo.system.StudyDetail;
import com.party.service.system.ActDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

@Service
public class ActDetailServiceImpl implements ActDetailService {
    @Autowired
    private ActDetailMapper actDetailMapper;

    @Override
    public PageResult<ActDetail> findPage(Map<String, Object> searchMap, int page, int size, String type) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap,type);
        Page<ActDetail> acts = (Page<ActDetail>)actDetailMapper.selectByExample(example);
        return new PageResult<ActDetail>(acts.getTotal(),acts.getResult());
    }

    private Example createExample(Map<String, Object> searchMap,String type){
        Example example=new Example(ActDetail.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null) {
            // 活动名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
        }
        criteria.andEqualTo("actId",type);
        return example;
    }
}
