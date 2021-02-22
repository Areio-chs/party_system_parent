package com.party.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonVo implements Serializable {
    private String id;//id
    private String sid;//学号

    private String name;//姓名

    private String sex;//性别

    private java.util.Date birth;//生日（年月日）

    private String grade;//年级

    private String classNum;//班级

    private String room;//宿舍号

    private String iamge;//个人照片

    private String idCard;//身份证

    private String phone;//电话号码

    private String email;//邮箱

    private String address;//家庭住址

    private String identity;//个人身份（学生）

    private String nativePlace;//籍贯

    private String residence;//户籍所在地

    private String nation;//民族

    private String duty;//职务（副部长）

    private String title;//职称

    private String aducation;//学历

    private String graInstitution;//毕业院校

    private java.util.Date workTime;//参加工作时间

    private java.util.Date joinTime;//入团时间

    private java.util.Date petitionConfirm;//落款时间

    private java.util.Date firstTalkTime;//首次谈话时间

    private String rewardPunishInfo;//奖惩信息

    private String accountId;//账户id

    private Integer typeId;//0积极分子1发展对象2预备党员

    private String integral;//累计积分

    private String qq;//QQ

    private String wechat;//微信

    private String generalId;//党总支id

    private String generalName;

    private String leagueBranchId;//团支部id

    private String leagueBranchName;

    private String partyId;//党支部id

    private String partyName;

    private String groupId;//党小组id

    private String groupName;

    private java.util.Date activistTime;//成为积极分子时间

    private java.util.Date developTime;

    private java.util.Date preMemberTime;

    private java.util.Date memberTime;//成为正式党员时间

    private String culture1Id;//培养人id1

    private String culture1Sid;

    private String culture1Name;

    private String culture2Id;//培养人id2

    private String culture2Sid;

    private String culture2Name;
}
