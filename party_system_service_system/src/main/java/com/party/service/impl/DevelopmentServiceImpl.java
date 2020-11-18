package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.DevelopmentMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Development;
import com.party.service.system.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

    @Autowired
    private DevelopmentMapper developmentMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Development> findAll() {
        return developmentMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Development> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Development> developments = (Page<Development>) developmentMapper.selectAll();
        return new PageResult<Development>(developments.getTotal(),developments.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Development> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return developmentMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Development> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Development> developments = (Page<Development>) developmentMapper.selectByExample(example);
        return new PageResult<Development>(developments.getTotal(),developments.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Development findById(String id) {
        return developmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param development
     */
    public void add(Development development) {
        developmentMapper.insert(development);
    }

    /**
     * 修改
     * @param development
     */
    public void update(Development development) {
        developmentMapper.updateByPrimaryKeySelective(development);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        developmentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Development.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 主键id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 是否积极分子
            if(searchMap.get("isActivist")!=null && !"".equals(searchMap.get("isActivist"))){
                criteria.andLike("isActivist","%"+searchMap.get("isActivist")+"%");
            }
            // 积极分子申请文件id
            if(searchMap.get("activistFileId")!=null && !"".equals(searchMap.get("activistFileId"))){
                criteria.andLike("activistFileId","%"+searchMap.get("activistFileId")+"%");
            }
            // 入党积极分子通过书链接
            if(searchMap.get("linkActivist")!=null && !"".equals(searchMap.get("linkActivist"))){
                criteria.andLike("linkActivist","%"+searchMap.get("linkActivist")+"%");
            }
            // 是否发展对象
            if(searchMap.get("isDevelop")!=null && !"".equals(searchMap.get("isDevelop"))){
                criteria.andLike("isDevelop","%"+searchMap.get("isDevelop")+"%");
            }
            // 发展对象通过书链接
            if(searchMap.get("linkDevelop")!=null && !"".equals(searchMap.get("linkDevelop"))){
                criteria.andLike("linkDevelop","%"+searchMap.get("linkDevelop")+"%");
            }
            // 发展对象申请文件id
            if(searchMap.get("developFileId")!=null && !"".equals(searchMap.get("developFileId"))){
                criteria.andLike("developFileId","%"+searchMap.get("developFileId")+"%");
            }
            // 是否预备党员
            if(searchMap.get("isPre")!=null && !"".equals(searchMap.get("isPre"))){
                criteria.andLike("isPre","%"+searchMap.get("isPre")+"%");
            }
            // 预备党员通过书链接
            if(searchMap.get("linkPre")!=null && !"".equals(searchMap.get("linkPre"))){
                criteria.andLike("linkPre","%"+searchMap.get("linkPre")+"%");
            }
            // 预备党员文件id
            if(searchMap.get("preFileId")!=null && !"".equals(searchMap.get("preFileId"))){
                criteria.andLike("preFileId","%"+searchMap.get("preFileId")+"%");
            }
            // 是否党员
            if(searchMap.get("isMember")!=null && !"".equals(searchMap.get("isMember"))){
                criteria.andLike("isMember","%"+searchMap.get("isMember")+"%");
            }
            // 党员申请书链接
            if(searchMap.get("linkMember")!=null && !"".equals(searchMap.get("linkMember"))){
                criteria.andLike("linkMember","%"+searchMap.get("linkMember")+"%");
            }
            // 是否毕业
            if(searchMap.get("isGraduate")!=null && !"".equals(searchMap.get("isGraduate"))){
                criteria.andLike("isGraduate","%"+searchMap.get("isGraduate")+"%");
            }
            // user表外键
            if(searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))){
                criteria.andLike("userId","%"+searchMap.get("userId")+"%");
            }
            // 培养人id1
            if(searchMap.get("culture1Id")!=null && !"".equals(searchMap.get("culture1Id"))){
                criteria.andLike("culture1Id","%"+searchMap.get("culture1Id")+"%");
            }
            // 培养人id2
            if(searchMap.get("culture2Id")!=null && !"".equals(searchMap.get("culture2Id"))){
                criteria.andLike("culture2Id","%"+searchMap.get("culture2Id")+"%");
            }

            // 当前状态
            if(searchMap.get("status")!=null ){
                criteria.andEqualTo("status",searchMap.get("status"));
            }

        }
        return example;
    }

}
