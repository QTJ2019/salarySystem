package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data

@TableName("fixed_item")
public class FixedItem implements Serializable {
//    @TableId(type = IdType.AUTO)
//    //固定项目id
//    private Integer id;
//
//    //员工id
//    private Integer employeeId;
//
//    //项目id
//    private Integer itemId;
//
//    //项目值
//    private Double value;
//
//    //日期
//    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date date;


    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(exist = false)
    private Integer departmentId;

    @TableField(exist = false)
    private String departmentName;

    @TableField(exist = false)
    private Integer stationId;

    @TableField(exist = false)
    private String stationName;

    private Integer employeeId;

    @TableField(exist = false)
    private String employeeName;

    private Integer itemId;

    @TableField(exist = false)
    private String itemName;

    private Double value;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date date;

    //limit properties

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private  Double minValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private Double maxValue;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private Date endDate;
}
