package com.party.pojo.system;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_study_detail")
@Data
public class StudyDetail implements Serializable {
    @Id
    private String id;//id

    private String type;

    private String userId;

    private String studyId;

    private String name;

    private String status;

}
