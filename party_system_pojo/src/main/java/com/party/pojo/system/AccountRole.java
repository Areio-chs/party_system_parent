package com.party.pojo.system;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="tb_account_role")
public class AccountRole {
    @Id
    private String accountId;//id

    @Id
    private Integer roleId;
}
