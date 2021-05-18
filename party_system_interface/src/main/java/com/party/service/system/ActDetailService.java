package com.party.service.system;

import com.party.entity.PageResult;
import com.party.pojo.system.ActDetail;

import java.util.Map;

public interface ActDetailService {
    PageResult<ActDetail> findPage(Map<String, Object> searchMap, int page, int size, String type);
}
