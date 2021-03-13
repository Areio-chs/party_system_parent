package com.party.dao;

import com.party.pojo.system.BaseUser;
import com.party.pojo.system.Group;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroupMapper extends Mapper<Group> {
    @Select("SELECT id,group_name,group_phone,group_address,group_time,group_note,party_id FROM tb_group WHERE group_name LIKE concat('%',#{name},'%')")
    public List<Group> findGroup(@Param("name") String name);
}
