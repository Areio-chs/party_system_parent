package com.party.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OptionVo implements Serializable {
    //值
    private String value;

    //名称
    private String label;

    //子级
    private List<OptionVo> children;

}
