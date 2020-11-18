package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.TaskMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Task;
import com.party.service.system.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Task> findAll() {
        return taskMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Task> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Task> tasks = (Page<Task>) taskMapper.selectAll();
        return new PageResult<Task>(tasks.getTotal(),tasks.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Task> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return taskMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Task> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Task> tasks = (Page<Task>) taskMapper.selectByExample(example);
        return new PageResult<Task>(tasks.getTotal(),tasks.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Task findById(String id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param task
     */
    public void add(Task task) {
        taskMapper.insert(task);
    }

    /**
     * 修改
     * @param task
     */
    public void update(Task task) {
        taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        taskMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 任务名称
            if(searchMap.get("taskName")!=null && !"".equals(searchMap.get("taskName"))){
                criteria.andLike("taskName","%"+searchMap.get("taskName")+"%");
            }
            // 任务详情
            if(searchMap.get("taskDetail")!=null && !"".equals(searchMap.get("taskDetail"))){
                criteria.andLike("taskDetail","%"+searchMap.get("taskDetail")+"%");
            }
            // 任务文件
            if(searchMap.get("taskId")!=null && !"".equals(searchMap.get("taskId"))){
                criteria.andLike("taskId","%"+searchMap.get("taskId")+"%");
            }
            // 发布人
            if(searchMap.get("publisher")!=null && !"".equals(searchMap.get("publisher"))){
                criteria.andLike("publisher","%"+searchMap.get("publisher")+"%");
            }
            // 审核人
            if(searchMap.get("reviewer")!=null && !"".equals(searchMap.get("reviewer"))){
                criteria.andLike("reviewer","%"+searchMap.get("reviewer")+"%");
            }
            // 任务积分
            if(searchMap.get("integral")!=null && !"".equals(searchMap.get("integral"))){
                criteria.andLike("integral","%"+searchMap.get("integral")+"%");
            }

            // 1审核完成任务0失败
            if(searchMap.get("status")!=null ){
                criteria.andEqualTo("status",searchMap.get("status"));
            }

        }
        return example;
    }

}
