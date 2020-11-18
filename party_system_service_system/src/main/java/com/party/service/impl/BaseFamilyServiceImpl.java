package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.BaseFamilyMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseFamily;
import com.party.service.system.BaseFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BaseFamilyServiceImpl implements BaseFamilyService {

    @Autowired
    private BaseFamilyMapper baseFamilyMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<BaseFamily> findAll() {
        return baseFamilyMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<BaseFamily> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<BaseFamily> baseFamilys = (Page<BaseFamily>) baseFamilyMapper.selectAll();
        return new PageResult<BaseFamily>(baseFamilys.getTotal(),baseFamilys.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<BaseFamily> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return baseFamilyMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<BaseFamily> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<BaseFamily> baseFamilys = (Page<BaseFamily>) baseFamilyMapper.selectByExample(example);
        return new PageResult<BaseFamily>(baseFamilys.getTotal(),baseFamilys.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public BaseFamily findById(String id) {
        return baseFamilyMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param baseFamily
     */
    public void add(BaseFamily baseFamily) {
        baseFamilyMapper.insert(baseFamily);
    }

    /**
     * 修改
     * @param baseFamily
     */
    public void update(BaseFamily baseFamily) {
        baseFamilyMapper.updateByPrimaryKeySelective(baseFamily);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        baseFamilyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(BaseFamily.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 父亲/母亲/妹妹
            if(searchMap.get("appellation")!=null && !"".equals(searchMap.get("appellation"))){
                criteria.andLike("appellation","%"+searchMap.get("appellation")+"%");
            }
            // name
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 群众/共青团员/党员
            if(searchMap.get("politicalStatus")!=null && !"".equals(searchMap.get("politicalStatus"))){
                criteria.andLike("politicalStatus","%"+searchMap.get("politicalStatus")+"%");
            }
            // 副部长
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }
            // user_id
            if(searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))){
                criteria.andLike("userId","%"+searchMap.get("userId")+"%");
            }

            // age
            if(searchMap.get("age")!=null ){
                criteria.andEqualTo("age",searchMap.get("age"));
            }

        }
        return example;
    }

}
