package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.PowerMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Power;
import com.party.service.system.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Power> findAll() {
        return powerMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Power> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Power> powers = (Page<Power>) powerMapper.selectAll();
        return new PageResult<Power>(powers.getTotal(),powers.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Power> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return powerMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Power> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Power> powers = (Page<Power>) powerMapper.selectByExample(example);
        return new PageResult<Power>(powers.getTotal(),powers.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Power findById(Integer id) {
        return powerMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param power
     */
    public void add(Power power) {
        powerMapper.insert(power);
    }

    /**
     * 修改
     * @param power
     */
    public void update(Power power) {
        powerMapper.updateByPrimaryKeySelective(power);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        powerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Power.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // pid
            if(searchMap.get("pid")!=null && !"".equals(searchMap.get("pid"))){
                criteria.andLike("pid","%"+searchMap.get("pid")+"%");
            }
            // name
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // url
            if(searchMap.get("url")!=null && !"".equals(searchMap.get("url"))){
                criteria.andLike("url","%"+searchMap.get("url")+"%");
            }

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // status
            if(searchMap.get("status")!=null ){
                criteria.andEqualTo("status",searchMap.get("status"));
            }

        }
        return example;
    }

}
