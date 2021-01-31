package com.party.service.system;

import com.party.vo.OptionVo;

import java.util.List;

public interface OptionService {
    List<OptionVo> getOption();

    List<OptionVo> getTransferOption();
}
