package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.BaseUserMapper;
import com.party.dao.StudyDetailMapper;
import com.party.dao.StudyMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseUser;
import com.party.pojo.system.Study;
import com.party.pojo.system.StudyDetail;
import com.party.service.system.BaseUserService;
import com.party.service.system.StudyService;
import com.party.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = StudyService.class )
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyMapper studyMapper;
    @Autowired
    private StudyDetailMapper studyDetailMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private IdWorker idWorker;
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
//    public void add(Study study) {
//        System.out.println(study.toString());
//        List<String> peopleList=study.getOrganization();
////        System.out.println(peopleList.toString());
////        System.out.println(peopleList.size());
//        for (int i=0;i<peopleList.size();i++){
//            study.setId(idWorker.nextId()+"");
//            String s = peopleList.get(i);
//            System.out.println(s);
//            String[] split = s.split(",");
//            study.setUserId(split[3].substring(1,20));
////            System.out.println(study.toString());
//            study.setIsFinish(0);
//            studyMapper.insert(study);
////            study.setUserId(list.get(3));
//        }
    @Transactional
    public void add(Study study) {
        System.out.println(study.toString());
        List<String> peopleList=study.getOrganization();
        study.setId(idWorker.nextId()+"");
        study.setIsFinish(0);
        studyMapper.insert(study);
        for (int i=0;i<peopleList.size();i++){
            StudyDetail studyDetail = new StudyDetail();
            studyDetail.setId(idWorker.nextId()+"");
            String s = peopleList.get(i);
            String[] split = s.split(",");
            studyDetail.setUserId(split[3].substring(1,20));

            //查出这个人的类型
            BaseUser baseUser=new BaseUser();
            baseUser.setId(split[3].substring(1,20));
            BaseUser baseUser1 = baseUserMapper.selectOne(baseUser);
            Integer typeId = baseUser1.getTypeId();
            String typeName="";
            if (typeId==0){
                typeName="积极分子";
            }
            if (typeId==1){
                typeName="发展对象";
            }
            if (typeId==2){
                typeName="预备党员";
            }
            if (typeId==3){
                typeName="党员";
            }
            studyDetail.setType(typeName);
            studyDetail.setName(baseUser1.getName());
            studyDetail.setStudyId(study.getId());
            studyDetail.setStatus("0");
            studyDetailMapper.insert(studyDetail);

        }



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
