package com.party.dao;

import com.party.pojo.system.BaseUser;
import com.party.vo.Activist;
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
            "            FROM tb_base_user bu,tb_development d WHERE bu.`id` = d.`user_id`")
    public List<Activist> findActivist();
}
