package com.party.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.party.dao.BaseUserMapper;
import com.party.dao.GeneralMapper;
import com.party.dao.GroupMapper;
import com.party.dao.PartyMapper;
import com.party.pojo.system.*;
import com.party.service.system.OptionService;
import com.party.vo.OptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.System;
import java.util.ArrayList;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private PartyMapper partyMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public List<OptionVo> getOption() {
        //1.查所有党总支
        List<General> generalList = generalMapper.selectAll();
        //2.查所有总支部
        List<Party> partyList = partyMapper.selectAll();
        //3.查所有党小组
        List<Group> groupList = groupMapper.selectAll();

        //创建list集合，用于存储最终封装数据
        List<OptionVo> optionList = new ArrayList<>();
        for (int i=0;i<generalList.size();i++){
            General general = generalList.get(i);
            OptionVo option = new OptionVo();
            option.setValue(general.getId());
            option.setLabel(general.getGeneralName());
            optionList.add(option);

            //在党总支循环遍历查询所有党支部
            //创建list集合封装每个党总支的总支部
            List<OptionVo> opartyList = new ArrayList<>();
            //遍历党支部list集合
            for (int m =0;m<partyList.size();m++){
                Party party = partyList.get(m);

                //判断党总支的总支部id与党支部的id是否一致
                    OptionVo option1 = new OptionVo();
                if (party.getGeneralId().equals(general.getId())){
                    option1.setValue(party.getId());
                    option1.setLabel(party.getPartyName());
                    opartyList.add(option1);
                }

                //在党支部循环遍历查询所有党小组
                // 创建list集合封装每个党支部的党小组
                List<OptionVo> ogroupList = new ArrayList<>();
                //遍历党小组集合
                for (int n = 0;n<groupList.size();n++){
                    Group group = groupList.get(n);
                    OptionVo option2 = new OptionVo();
//                    System.out.println(group.getPartyId()+"---------");
//                    System.out.println(party.getId()+"!!!!!!!!!!");
                    if (group.getPartyId().equals(party.getId())){
                        option2.setValue(group.getId());
                        option2.setLabel(group.getGroupName());
                        ogroupList.add(option2);
                    }

                }
                option1.setChildren(ogroupList);
            }
            option.setChildren(opartyList);
        }


        for (OptionVo option3:optionList){
            List<OptionVo> children = option3.getChildren();
            for (OptionVo option4:children){
                List<OptionVo> children1 = option4.getChildren();
                for (int k=0;k<children1.size();k++){
                    List<OptionVo> userList = new ArrayList<>();
                    String value = children1.get(k).getValue();//查出这个小组的所有党员
                    Example example=new Example(BaseUser.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("typeId",3);//党员的类型是id
                    criteria.andEqualTo("groupId",value);
                    List<BaseUser> baseUsers = baseUserMapper.selectByExample(example);

                    for (BaseUser baseUser:baseUsers) {
                        OptionVo option = new OptionVo();
                        System.out.println(baseUser.toString());
                        option.setValue(baseUser.getId());
                        option.setLabel(baseUser.getName());
                        userList.add(option);
                        for (OptionVo op :userList){
                            System.out.println(op.toString());
                        }
                    }

                    children1.get(k).setChildren(userList);
                }

            }
            
        }
        return optionList;
    }

    @Override
    public List<OptionVo> getTransferOption() {
        //1.查所有党总支
        List<General> generalList = generalMapper.selectAll();
        //2.查所有总支部
        List<Party> partyList = partyMapper.selectAll();
        //3.查所有党小组
        List<Group> groupList = groupMapper.selectAll();

        //创建list集合，用于存储最终封装数据
        List<OptionVo> optionList = new ArrayList<>();
        for (int i=0;i<generalList.size();i++){
            General general = generalList.get(i);
            OptionVo option = new OptionVo();
            option.setValue(general.getId());
            option.setLabel(general.getGeneralName());
            optionList.add(option);

            //在党总支循环遍历查询所有党支部
            //创建list集合封装每个党总支的总支部
            List<OptionVo> opartyList = new ArrayList<>();
            //遍历党支部list集合
            for (int m =0;m<partyList.size();m++){
                Party party = partyList.get(m);

                //判断党总支的总支部id与党支部的id是否一致
                OptionVo option1 = new OptionVo();
                if (party.getGeneralId().equals(general.getId())){
                    option1.setValue(party.getId());
                    option1.setLabel(party.getPartyName());
                    opartyList.add(option1);
                }

                //在党支部循环遍历查询所有党小组
                // 创建list集合封装每个党支部的党小组
                List<OptionVo> ogroupList = new ArrayList<>();
                //遍历党小组集合
                for (int n = 0;n<groupList.size();n++){
                    Group group = groupList.get(n);
                    OptionVo option2 = new OptionVo();
//                    System.out.println(group.getPartyId()+"---------");
//                    System.out.println(party.getId()+"!!!!!!!!!!");
                    if (group.getPartyId().equals(party.getId())){
                        option2.setValue(group.getId());
                        option2.setLabel(group.getGroupName());
                        ogroupList.add(option2);
                    }

                }
                option1.setChildren(ogroupList);
            }
            option.setChildren(opartyList);
        }
        return optionList;
    }

    @Override
    public List<OptionVo> getPeopleOption() {
        //1.查所有党总支
        List<General> generalList = generalMapper.selectAll();
        //2.查所有总支部
        List<Party> partyList = partyMapper.selectAll();
        //3.查所有党小组
        List<Group> groupList = groupMapper.selectAll();

        //创建list集合，用于存储最终封装数据
        List<OptionVo> optionList = new ArrayList<>();
        for (int i=0;i<generalList.size();i++){
            General general = generalList.get(i);
            OptionVo option = new OptionVo();
            option.setValue(general.getId());
            option.setLabel(general.getGeneralName());
            optionList.add(option);

            //在党总支循环遍历查询所有党支部
            //创建list集合封装每个党总支的总支部
            List<OptionVo> opartyList = new ArrayList<>();
            //遍历党支部list集合
            for (int m =0;m<partyList.size();m++){
                Party party = partyList.get(m);

                //判断党总支的总支部id与党支部的id是否一致
                OptionVo option1 = new OptionVo();
                if (party.getGeneralId().equals(general.getId())){
                    option1.setValue(party.getId());
                    option1.setLabel(party.getPartyName());
                    opartyList.add(option1);
                }

                //在党支部循环遍历查询所有党小组
                // 创建list集合封装每个党支部的党小组
                List<OptionVo> ogroupList = new ArrayList<>();
                //遍历党小组集合
                for (int n = 0;n<groupList.size();n++){
                    Group group = groupList.get(n);
                    OptionVo option2 = new OptionVo();
                    if (group.getPartyId().equals(party.getId())){
                        option2.setValue(group.getId());
                        option2.setLabel(group.getGroupName());
                        ogroupList.add(option2);
                    }

                }
                option1.setChildren(ogroupList);
            }
            option.setChildren(opartyList);
        }


        for (OptionVo option3:optionList){
            List<OptionVo> children = option3.getChildren();
            for (OptionVo option4:children){
                List<OptionVo> children1 = option4.getChildren();
                for (int k=0;k<children1.size();k++){
                    List<OptionVo> userList = new ArrayList<>();
                    String value = children1.get(k).getValue();//查出这个小组的所有党员
                    Example example=new Example(BaseUser.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("groupId",value);
                    List<BaseUser> baseUsers = baseUserMapper.selectByExample(example);
                    //0积极分子1发展对象2预备党员3正式党员
                    String level="积极分子";
                    for (BaseUser baseUser:baseUsers) {
                        OptionVo option = new OptionVo();
                        System.out.println(baseUser.toString());
                        option.setValue(baseUser.getId());

                        if (baseUser.getTypeId()==0) {
                            level="(积极分子)";
                        }
                        if (baseUser.getTypeId()==1){
                            level="(发展对象)";
                        }
                        if (baseUser.getTypeId()==2){
                            level="(预备党员)";
                        }
                        if (baseUser.getTypeId()==3){
                            level="(党员)";
                        }
                        option.setLabel(baseUser.getName()+level);
                        userList.add(option);
                        for (OptionVo op :userList){
                            System.out.println(op.toString());
                        }
                    }

                    children1.get(k).setChildren(userList);
                }

            }

        }
        return optionList;
    }

}
