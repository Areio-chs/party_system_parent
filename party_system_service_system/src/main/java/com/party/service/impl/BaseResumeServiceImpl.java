package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.BaseResumeMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseResume;
import com.party.service.system.BaseResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BaseResumeServiceImpl implements BaseResumeService {

    @Autowired
    private BaseResumeMapper baseResumeMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<BaseResume> findAll() {
        return baseResumeMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<BaseResume> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<BaseResume> baseResumes = (Page<BaseResume>) baseResumeMapper.selectAll();
        return new PageResult<BaseResume>(baseResumes.getTotal(),baseResumes.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<BaseResume> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return baseResumeMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<BaseResume> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<BaseResume> baseResumes = (Page<BaseResume>) baseResumeMapper.selectByExample(example);
        return new PageResult<BaseResume>(baseResumes.getTotal(),baseResumes.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public BaseResume findById(String id) {
        return baseResumeMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param baseResume
     */
    public void add(BaseResume baseResume) {
        baseResumeMapper.insert(baseResume);
    }

    /**
     * 修改
     * @param baseResume
     */
    public void update(BaseResume baseResume) {
        baseResumeMapper.updateByPrimaryKeySelective(baseResume);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        baseResumeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(BaseResume.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 在何地、何单位工作（学习），任何职
            if(searchMap.get("where")!=null && !"".equals(searchMap.get("where"))){
                criteria.andLike("where","%"+searchMap.get("where")+"%");
            }
            // 证明人
            if(searchMap.get("witness")!=null && !"".equals(searchMap.get("witness"))){
                criteria.andLike("witness","%"+searchMap.get("witness")+"%");
            }
            // user_id
            if(searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))){
                criteria.andLike("userId","%"+searchMap.get("userId")+"%");
            }


        }
        return example;
    }

}
