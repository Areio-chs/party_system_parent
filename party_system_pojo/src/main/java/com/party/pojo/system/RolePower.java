package com.party.pojo.system;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="tb_role_power")
public class RolePower {
    @Id
    private Integer roleId;

    @Id
    private Integer powerId;
}
