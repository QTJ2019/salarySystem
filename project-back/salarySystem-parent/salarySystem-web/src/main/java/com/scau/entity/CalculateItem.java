package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data

@TableName("calculate_item")
public class CalculateItem {
    @TableId(type = IdType.AUTO)
    //固定项目id
    private Integer id;

    //员工id
    private Integer employee_id;

    //项目id
    private Integer item_id;

    //计算表达式
    private  String expression;

    //项目值
    private Double value;

    //日期
    private Date date;

}
