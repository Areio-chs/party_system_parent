package com.party.service.system;
import com.party.entity.PageResult;
import com.party.excel.ActivistData;
import com.party.excel.DevelopmentData;
import com.party.excel.MemberData;
import com.party.excel.PreMemberData;
import com.party.pojo.system.BaseUser;
import com.party.vo.ActivistVo;
import com.party.vo.CommonVo;
import com.party.vo.DevelopmentVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * baseUser业务逻辑层
 */
public interface BaseUserService {


    public List<ActivistData> findActivistOut();


    public PageResult<BaseUser> findPage(int page, int size);


    public List<BaseUser> findList(Map<String,Object> searchMap);


    public PageResult<BaseUser> findPage(Map<String,Object> searchMap,int page, int size);



//    public ActivistVo findById(String id);

    public CommonVo findCommonById(String id,int type);

    public List<String> handleCul(String cultureId);

//    public void add(ActivistVo activistVo);

    public void excelAdd(CommonVo commonVo);


    public void delete(String id);

//    void addActivist(BaseUser baseUser);

//    public PageResult<ActivistVo>  findActivist(Map<String,Object> searchMap, int page, int size);

    public PageResult<CommonVo> findCommonPage(Map<String,Object> searchMap, int page, int size, int type);

    public PageResult<CommonVo> findPotentialPage(Map<String,Object> searchMap, int page, int size, int type, int status);

    public void addCommon(CommonVo commonVo,int type);

    public List<BaseUser> findTest(String activistName);


    void transfer(Map<String,Object> formLabelAlign);


    int uniqueSid(String sid);

    void changeCul(Map<String, Object> dynamicValidateForm);

    void excelAddDevelopment(CommonVo commonVo);

    PageResult<DevelopmentVo> findDevelopment(Map<String, Object> searchMap, int page, int size);

    void excelAddMember(CommonVo commonVo);

    void updateCommon(CommonVo commonVo,int type);

    void excelAddPreMember(CommonVo commonVo);

    List<DevelopmentData> findDevelopmentOut();

    List<PreMemberData> findPreMemberOut();

    List<MemberData> findMemberOut();
}
