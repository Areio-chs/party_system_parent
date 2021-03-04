package com.party.excel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

import java.io.Serializable;

@Data
@ContentRowHeight(20)//数据行的行高
@ColumnWidth(20)//列宽
@ExcelIgnoreUnannotated//表示没有加@ExcelProperty的注解不会参与读写
public class PreMemberData implements Serializable {
    @ExcelProperty(value = "姓名",index = 0)
    private String name;//姓名

    @ExcelProperty(value = "性别",index = 1)
    private String sex;//性别

    @ExcelProperty(value = "身份证号",index = 2)
    private String idCard;//身份证

    @ExcelProperty(value = "籍贯",index = 3)
    private String nativePlace;//籍贯

    @ExcelProperty(value = "户籍所在地",index = 4)
    private String residence;//户籍所在地

    @ExcelProperty(value = "民族",index = 5)
    private String nation;//民族

    @ExcelProperty(value = "联系电话",index = 6)
    private String phone;//电话号码

    @ExcelProperty(value = "年级",index = 7)
    private String grade;//年级

    @ExcelProperty(value = "班级",index = 8)
    private String classNum;//班级

    @ExcelProperty(value = "学号",index = 9)
    private String sid;//学号

    @ExcelProperty(value = "宿舍",index = 10)
    private String room;//宿舍号

    @ExcelProperty(value = "职务",index = 11)
    private String duty;//职务（副部长）

    @ExcelProperty(value = "职称",index = 12)
    private String title;//职称

    @ExcelProperty(value = "学历",index = 13)
    private String aducation;//学历

    @ExcelProperty(value = "所在党总支",index = 14)
    private String generalName;

    @ExcelProperty(value = "所在党支部",index = 15)
    private String partyName;

    @ExcelProperty(value = "所在党小组",index = 16)
    private String groupName;

    @ExcelProperty(value = "所在团支部",index = 17)
    private String leagueBranchName;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "申请书日期",index = 18)
    private java.util.Date petitionConfirm;//落款时间

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "确定为积极分子日期",index = 19)
    private java.util.Date activistTime;//成为积极分子时间

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "确定为发展对象日期",index = 20)
    private java.util.Date developTime;//成为发展对象时间

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "确定为预备党员日期",index = 21)
    private java.util.Date preMemberTime;

//    @ExcelProperty(value = "票数",index = 21)这个预备党员把
//    private String vote;

    @ExcelProperty(value = "培养联系人1",index = 22)
    private String culture1Name;

    @ExcelProperty(value = "培养联系人1学号",index = 23)
    private String culture1Sid;

    @ExcelProperty(value = "培养联系人2",index = 24)
    private String culture2Name;

    @ExcelProperty(value = "培养联系人2学号",index = 25)
    private String culture2Sid;
}
