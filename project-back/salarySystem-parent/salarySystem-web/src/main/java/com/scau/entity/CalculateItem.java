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

@TableName("calculate_item")
public class CalculateItem implements Serializable {
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

    private Integer value;

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

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private Date endDate;

}
