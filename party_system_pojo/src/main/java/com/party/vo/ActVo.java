package com.party.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ActVo implements Serializable {
    private String id;//id


    private String name;//活动名称

    private java.util.Date time;//活动时间

    private Integer typeId;//活动类型id

    private String note;//活动备注

    private String address;//活动地点

    private String publisher;//发布人

    private Integer groupId;

    private Integer joinNum;//参与人数

    private String typeName;//活动类型的名称


    private List<String> organization;


}
