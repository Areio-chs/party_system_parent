package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.StudyMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Study;
import com.party.service.system.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyMapper studyMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Study> findAll() {
        return studyMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Study> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Study> studys = (Page<Study>) studyMapper.selectAll();
        return new PageResult<Study>(studys.getTotal(),studys.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Study> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return studyMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Study> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Study> studys = (Page<Study>) studyMapper.selectByExample(example);
        return new PageResult<Study>(studys.getTotal(),studys.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Study findById(String id) {
        return studyMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param study
     */
    public void add(Study study) {
        studyMapper.insert(study);
    }

    /**
     * 修改
     * @param study
     */
    public void update(Study study) {
        studyMapper.updateByPrimaryKeySelective(study);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        studyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Study.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 学习内容
            if(searchMap.get("studyName")!=null && !"".equals(searchMap.get("studyName"))){
                criteria.andLike("studyName","%"+searchMap.get("studyName")+"%");
            }
            // 学习获得积分
            if(searchMap.get("integral")!=null && !"".equals(searchMap.get("integral"))){
                criteria.andLike("integral","%"+searchMap.get("integral")+"%");
            }
            // 备注
            if(searchMap.get("studyNote")!=null && !"".equals(searchMap.get("studyNote"))){
                criteria.andLike("studyNote","%"+searchMap.get("studyNote")+"%");
            }
            // 空白字段
            if(searchMap.get("studyMore")!=null && !"".equals(searchMap.get("studyMore"))){
                criteria.andLike("studyMore","%"+searchMap.get("studyMore")+"%");
            }
            // 外键用户id
            if(searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))){
                criteria.andLike("userId","%"+searchMap.get("userId")+"%");
            }


        }
        return example;
    }

}
