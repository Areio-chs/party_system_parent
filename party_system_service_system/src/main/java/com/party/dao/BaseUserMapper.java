package com.party.dao;

import com.party.pojo.system.BaseUser;
import com.party.vo.ActivistVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseUserMapper extends Mapper<BaseUser> {
    @Select("SELECT  bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
            "            email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
            "            work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
            "            reward_punish_info rewardPunishInfo,account_id accountId,type_id typeId,integral,qq,wechat," +
            "            league_branch_id leagueBranchId ,general_id generalId,party_id partyId,group_id groupId," +
            "            activist_time activistTime,culture1_id culture1Id,culture2_id culture2Id" +
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id` and bu.type_id=0 and bu.name LIKE concat('%',#{name},'%')")
    public List<ActivistVo> findActivist(@Param("name") String name);

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

//    @Select("SELECT  bu.id,sid,NAME,sex,birth,grade,class_num classNum,room,iamge,id_card idCard,phone," +
//            "                       email,address,identity,native_place nativePlace,residence,nation,duty,title,aducation,gra_institution graInstitution," +
//            "                       work_time workTime,join_time joinTime,petition_confirm petitionConfirm,first_talk_time firstTalkTime," +
//            "                       reward_punish_info rewardPunishInfo,bu.account_id accountId,type_id typeId,integral,qq,wechat," +
//            "                       league_branch_id leagueBranchId ,bu.general_id generalId,bu.party_id partyId,bu.group_id groupId," +
//            "                       activist_time activistTime,culture1_id culture1Id,culture2_id culture2Id,general_name generalName,party_name partyName,group_name groupName" +
//            "                       FROM tb_base_user bu,tb_development d ,tb_general,tb_party,tb_group  WHERE bu.`id` = d.`user_id`  AND bu.type_id=0 AND tb_general.`id`=bu.`general_id`" +
//            "                        AND tb_party.id=bu.`party_id` AND  tb_group.`id`=bu.`group_id` AND bu.`id`=#{id} ")



}
