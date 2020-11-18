package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.FileUserMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.FileUser;
import com.party.service.system.FileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class FileUserServiceImpl implements FileUserService {

    @Autowired
    private FileUserMapper fileUserMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<FileUser> findAll() {
        return fileUserMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<FileUser> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<FileUser> fileUsers = (Page<FileUser>) fileUserMapper.selectAll();
        return new PageResult<FileUser>(fileUsers.getTotal(),fileUsers.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<FileUser> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return fileUserMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<FileUser> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<FileUser> fileUsers = (Page<FileUser>) fileUserMapper.selectByExample(example);
        return new PageResult<FileUser>(fileUsers.getTotal(),fileUsers.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public FileUser findById(String id) {
        return fileUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param fileUser
     */
    public void add(FileUser fileUser) {
        fileUserMapper.insert(fileUser);
    }

    /**
     * 修改
     * @param fileUser
     */
    public void update(FileUser fileUser) {
        fileUserMapper.updateByPrimaryKeySelective(fileUser);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        fileUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(FileUser.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 主键id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 文件名
            if(searchMap.get("fileName")!=null && !"".equals(searchMap.get("fileName"))){
                criteria.andLike("fileName","%"+searchMap.get("fileName")+"%");
            }
            // 上传路径
            if(searchMap.get("filePath")!=null && !"".equals(searchMap.get("filePath"))){
                criteria.andLike("filePath","%"+searchMap.get("filePath")+"%");
            }
            // 备注
            if(searchMap.get("fileNote")!=null && !"".equals(searchMap.get("fileNote"))){
                criteria.andLike("fileNote","%"+searchMap.get("fileNote")+"%");
            }
            // user表外键
            if(searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))){
                criteria.andLike("userId","%"+searchMap.get("userId")+"%");
            }
            // 文件类型
            if(searchMap.get("typeName")!=null && !"".equals(searchMap.get("typeName"))){
                criteria.andLike("typeName","%"+searchMap.get("typeName")+"%");
            }

            // 文件类型id 0表示图片1表示文件
            if(searchMap.get("fileTypeId")!=null ){
                criteria.andEqualTo("fileTypeId",searchMap.get("fileTypeId"));
            }
            // 1是申请入党的/2是申请积极分子的/3是申请发展对象的
            if(searchMap.get("photoId")!=null ){
                criteria.andEqualTo("photoId",searchMap.get("photoId"));
            }

        }
        return example;
    }

}
