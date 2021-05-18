package com.party.service.system;

import com.party.entity.PageResult;
import com.party.pojo.system.Study;
import com.party.pojo.system.StudyDetail;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface StudyDetailService {
    public PageResult<StudyDetail> findPage(@RequestBody Map<String,Object> searchMap, int page, int size, String type);

    void updateStatus(String type,String myStatus);
}
