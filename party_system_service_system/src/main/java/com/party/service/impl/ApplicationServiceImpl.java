package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.ApplicationMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Application;
import com.party.service.system.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Application> findAll() {
        return applicationMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Application> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Application> applications = (Page<Application>) applicationMapper.selectAll();
        return new PageResult<Application>(applications.getTotal(),applications.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Application> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return applicationMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Application> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Application> applications = (Page<Application>) applicationMapper.selectByExample(example);
        return new PageResult<Application>(applications.getTotal(),applications.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Application findById(String id) {
        return applicationMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param application
     */
    public void add(Application application) {
        applicationMapper.insert(application);
    }

    /**
     * 修改
     * @param application
     */
    public void update(Application application) {
        applicationMapper.updateByPrimaryKeySelective(application);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        applicationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Application.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // name
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // sex
            if(searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))){
                criteria.andLike("sex","%"+searchMap.get("sex")+"%");
            }
            // nation
            if(searchMap.get("nation")!=null && !"".equals(searchMap.get("nation"))){
                criteria.andLike("nation","%"+searchMap.get("nation")+"%");
            }
            // native_place
            if(searchMap.get("nativePlace")!=null && !"".equals(searchMap.get("nativePlace"))){
                criteria.andLike("nativePlace","%"+searchMap.get("nativePlace")+"%");
            }
            // id_card
            if(searchMap.get("idCard")!=null && !"".equals(searchMap.get("idCard"))){
                criteria.andLike("idCard","%"+searchMap.get("idCard")+"%");
            }
            // phone
            if(searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))){
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
            }
            // class_num
            if(searchMap.get("classNum")!=null && !"".equals(searchMap.get("classNum"))){
                criteria.andLike("classNum","%"+searchMap.get("classNum")+"%");
            }
            // account_id
            if(searchMap.get("accountId")!=null && !"".equals(searchMap.get("accountId"))){
                criteria.andLike("accountId","%"+searchMap.get("accountId")+"%");
            }
            // email
            if(searchMap.get("email")!=null && !"".equals(searchMap.get("email"))){
                criteria.andLike("email","%"+searchMap.get("email")+"%");
            }

            // 1仍为申请人0为其他
            if(searchMap.get("status")!=null ){
                criteria.andEqualTo("status",searchMap.get("status"));
            }

        }
        return example;
    }

}
