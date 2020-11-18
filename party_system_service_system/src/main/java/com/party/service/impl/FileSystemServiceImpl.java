package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.FileSystemMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.FileSystem;
import com.party.service.system.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Autowired
    private FileSystemMapper fileSystemMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<FileSystem> findAll() {
        return fileSystemMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<FileSystem> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<FileSystem> fileSystems = (Page<FileSystem>) fileSystemMapper.selectAll();
        return new PageResult<FileSystem>(fileSystems.getTotal(),fileSystems.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<FileSystem> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return fileSystemMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<FileSystem> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<FileSystem> fileSystems = (Page<FileSystem>) fileSystemMapper.selectByExample(example);
        return new PageResult<FileSystem>(fileSystems.getTotal(),fileSystems.getResult());
    }

    /**
     * 根据Id查询
     * @param sys_id
     * @return
     */
    public FileSystem findById(String sysId) {
        return fileSystemMapper.selectByPrimaryKey(sysId);
    }

    /**
     * 新增
     * @param fileSystem
     */
    public void add(FileSystem fileSystem) {
        fileSystemMapper.insert(fileSystem);
    }

    /**
     * 修改
     * @param fileSystem
     */
    public void update(FileSystem fileSystem) {
        fileSystemMapper.updateByPrimaryKeySelective(fileSystem);
    }

    /**
     *  删除
     * @param sys_id
     */
    public void delete(String sysId) {
        fileSystemMapper.deleteByPrimaryKey(sysId);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(FileSystem.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 主键id
            if(searchMap.get("sysId")!=null && !"".equals(searchMap.get("sysId"))){
                criteria.andLike("sysId","%"+searchMap.get("sysId")+"%");
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
            // 1是活动文件/2是任务文件
            if(searchMap.get("photoId")!=null ){
                criteria.andEqualTo("photoId",searchMap.get("photoId"));
            }

        }
        return example;
    }

}
