package com.party.dao;

import com.party.excel.ActivistData;
import com.party.excel.DevelopmentData;
import com.party.excel.MemberData;
import com.party.excel.PreMemberData;
import com.party.pojo.system.BaseUser;
import com.party.vo.ActivistVo;
import com.party.vo.CommonVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseUserMapper extends Mapper<BaseUser> {
    //分页查找积极分子
    @Select("SELECT  bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
            "            email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
            "            work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
            "            reward_punish_info rewardPunishInfo,account_id accountId,type_id typeId,integral,qq,wechat," +
            "            league_branch_id leagueBranchId ,general_id generalId,party_id partyId,group_id groupId," +
            "            league_branch_name leagueBranchName, general_name generalName,party_name partyName,group_name groupName,"+
            "            activist_time activistTime,culture1_id culture1Id,culture2_id culture2Id,culture1_name culture1Name,culture2_name culture2Name" +
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=0 and bu.name LIKE concat('%',#{name},'%') and bu.general_id like concat('%',#{generalId},'%') and bu.party_id like concat('%',#{partyId},'%') and bu.group_id like concat('%',#{groupId},'%') and bu.league_branch_id like concat('%',#{leagueBranchId},'%') " )
    public List<CommonVo> findActivist(@Param("name") String name , @Param("generalId") String generalId, @Param("partyId") String partyId, @Param("groupId") String groupId, @Param("leagueBranchId") String leagueBranchId);
    //分页查找发展对象
    @Select("SELECT  bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
            "            email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
            "            work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
            "            reward_punish_info rewardPunishInfo,account_id accountId,type_id typeId,integral,qq,wechat," +
            "            league_branch_id leagueBranchId ,general_id generalId,party_id partyId,group_id groupId," +
            "            league_branch_name leagueBranchName, general_name generalName,party_name partyName,group_name groupName,"+
            "            activist_time activistTime,develop_time developTime,pre_member_time preMemberTime,member_time memberTime, culture1_id culture1Id,culture2_id culture2Id,culture1_name culture1Name,culture2_name culture2Name" +
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=#{type} and bu.name LIKE concat('%',#{name},'%') and bu.general_id like concat('%',#{generalId},'%') and bu.party_id like concat('%',#{partyId},'%') and bu.group_id like concat('%',#{groupId},'%') and bu.league_branch_id like concat('%',#{leagueBranchId},'%') " )
    public List<CommonVo> findCommon(@Param("name") String name , @Param("generalId") String generalId, @Param("partyId") String partyId, @Param("groupId") String groupId, @Param("leagueBranchId") String leagueBranchId,@Param("type") int type);


    //分页查找发展对象
    @Select("SELECT      bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
            "            email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
            "            work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
            "            reward_punish_info rewardPunishInfo,account_id accountId,type_id typeId,integral,qq,wechat," +
            "            league_branch_id leagueBranchId ,general_id generalId,party_id partyId,group_id groupId," +
            "            league_branch_name leagueBranchName, general_name generalName,party_name partyName,group_name groupName,"+
            "            activist_time activistTime,develop_time developTime,pre_member_time preMemberTime,member_time memberTime, culture1_id culture1Id,culture2_id culture2Id,culture1_name culture1Name,culture2_name culture2Name," +
            "            d.status" +
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=#{type} and bu.name LIKE concat('%',#{name},'%') and d.status=#{status}" )
    public List<CommonVo> findPotential(@Param("name") String name , @Param("type") int type, @Param("status") int status);

    @Select("select * from tb_base_user WHERE NAME LIKE concat('%',#{name},'%')")
//    @Select("SELECT * FROM tb_base_user WHERE NAME=#{name}")
    public List<BaseUser> findTest(@Param("name") String name);

    @Select("SELECT  bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
            "                       email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
            "                       work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
            "                       reward_punish_info rewardPunishInfo,bu.account_id accountId,type_id typeId,integral,qq,wechat," +
            "                       league_branch_id leagueBranchId ,bu.general_id generalId,bu.party_id partyId,bu.group_id groupId," +
            "                       activist_time activistTime,culture1_id culture1Id,culture2_id culture2Id" +
            "                       FROM tb_base_user bu,tb_development d   WHERE bu.`id` = d.`user_id`  AND bu.type_id=0 " +
            "                        AND bu.`id`=#{id} ")
    public ActivistVo findById(@Param("id") String id);

    @Select("SELECT  bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
            "                       email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
            "                       work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
            "                       reward_punish_info rewardPunishInfo,bu.account_id accountId,type_id typeId,integral,qq,wechat," +
            "                       league_branch_id leagueBranchId ,bu.general_id generalId,bu.party_id partyId,bu.group_id groupId," +
            "                       activist_time activistTime,develop_time developTime,pre_member_time preMemberTime,member_time memberTime, culture1_id culture1Id,culture2_id culture2Id" +
            "                       FROM tb_base_user bu,tb_development d   WHERE bu.`id` = d.`user_id`  AND bu.type_id=#{type} " +
            "                        AND bu.`id`=#{id} ")
    public CommonVo findCommonById(@Param("id") String id,@Param("type") int type);

    @Select("SELECT  NAME,sex,id_card idCard,native_place nativePlace,residence,nation,phone,grade,class_num classNum,sid,room," +
            "           duty,title,aducation,general_name generalName,party_name partyName,group_name groupName,league_branch_name leagueBranchName,petition_confirm petitionConfirm," +
            "            activist_time activistTime,culture1_name culture1Name,culture1_sid culture1Sid,culture2_name culture2Name,culture2_sid culture2Sid"+
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=0")
    public List<ActivistData> findActivistOut();

    @Select("SELECT  NAME,sex,id_card idCard,native_place nativePlace,residence,nation,phone,grade,class_num classNum,sid,room," +
            "           duty,title,aducation,general_name generalName,party_name partyName,group_name groupName,league_branch_name leagueBranchName,petition_confirm petitionConfirm," +
            "            activist_time activistTime,develop_time developTime,culture1_name culture1Name,culture1_sid culture1Sid,culture2_name culture2Name,culture2_sid culture2Sid"+
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=1")
    public List<DevelopmentData> findDevelopmentOut();
    @Select("SELECT  NAME,sex,id_card idCard,native_place nativePlace,residence,nation,phone,grade,class_num classNum,sid,room," +
            "           duty,title,aducation,general_name generalName,party_name partyName,group_name groupName,league_branch_name leagueBranchName,petition_confirm petitionConfirm," +
            "            activist_time activistTime,develop_time developTime,pre_member_time preMemberTime,culture1_name culture1Name,culture1_sid culture1Sid,culture2_name culture2Name,culture2_sid culture2Sid"+
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=2")
    List<PreMemberData> findPreMemberOut();
    @Select("SELECT  NAME,sex,id_card idCard,native_place nativePlace,residence,nation,phone,grade,class_num classNum,sid,room," +
            "           duty,title,aducation,general_name generalName,party_name partyName,group_name groupName,league_branch_name leagueBranchName,petition_confirm petitionConfirm," +
            "            activist_time activistTime,develop_time developTime,pre_member_time preMemberTime,member_time memberTime"+
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=3")
    List<MemberData> findMemberOut();
//    @Select("SELECT  bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
//            "                       email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
//            "                       work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
//            "                       reward_punish_info rewardPunishInfo,bu.account_id accountId,type_id typeId,integral,qq,wechat," +
//            "                       league_branch_id leagueBranchId ,bu.general_id generalId,bu.party_id partyId,bu.group_id groupId," +
//            "                       activist_time activistTime,culture1_id culture1Id,culture2_id culture2Id,general_name generalName,party_name partyName,group_name groupName" +
//            "                       FROM tb_base_user bu,tb_development d ,tb_general,tb_party,tb_group  WHERE bu.`id` = d.`user_id`  AND bu.type_id=0 AND tb_general.`id`=bu.`general_id`" +
//            "                        AND tb_party.id=bu.`party_id` AND  tb_group.`id`=bu.`group_id` AND bu.`id`=#{id} ")



}
