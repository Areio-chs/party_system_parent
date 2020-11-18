package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.FundMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Fund;
import com.party.service.system.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private FundMapper fundMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Fund> findAll() {
        return fundMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Fund> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Fund> funds = (Page<Fund>) fundMapper.selectAll();
        return new PageResult<Fund>(funds.getTotal(),funds.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Fund> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return fundMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Fund> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Fund> funds = (Page<Fund>) fundMapper.selectByExample(example);
        return new PageResult<Fund>(funds.getTotal(),funds.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Fund findById(String id) {
        return fundMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param fund
     */
    public void add(Fund fund) {
        fundMapper.insert(fund);
    }

    /**
     * 修改
     * @param fund
     */
    public void update(Fund fund) {
        fundMapper.updateByPrimaryKeySelective(fund);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        fundMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Fund.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 缴费内容
            if(searchMap.get("fundName")!=null && !"".equals(searchMap.get("fundName"))){
                criteria.andLike("fundName","%"+searchMap.get("fundName")+"%");
            }
            // 缴费类型
            if(searchMap.get("fundType")!=null && !"".equals(searchMap.get("fundType"))){
                criteria.andLike("fundType","%"+searchMap.get("fundType")+"%");
            }
            // 备注
            if(searchMap.get("fundNote")!=null && !"".equals(searchMap.get("fundNote"))){
                criteria.andLike("fundNote","%"+searchMap.get("fundNote")+"%");
            }
            // 外键党员
            if(searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))){
                criteria.andLike("userId","%"+searchMap.get("userId")+"%");
            }
            // 外键党支部
            if(searchMap.get("partyId")!=null && !"".equals(searchMap.get("partyId"))){
                criteria.andLike("partyId","%"+searchMap.get("partyId")+"%");
            }


        }
        return example;
    }

}
