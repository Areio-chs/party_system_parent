package com.party.pojo.system;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.io.Serializable;

@Data
@Table(name="tb_act_detail")

public class ActDetail implements Serializable {

    @Id
    private String id;//id

    private String typeName;

    private String userId;

    private String actId;

    private String name;

    private String status;

}
