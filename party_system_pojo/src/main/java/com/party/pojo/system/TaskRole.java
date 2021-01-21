package com.party.pojo.system;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="tb_task_role")
public class TaskRole {
    @Id
    private String taskId;
    @Id
    private Integer roleId;
}
