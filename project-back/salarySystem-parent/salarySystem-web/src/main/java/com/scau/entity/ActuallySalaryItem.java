package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data

@TableName("actually_salary_item")
public class ActuallySalaryItem {
    @TableId(type = IdType.AUTO)
    //固定项目id
    private Integer id;

    //员工id
    private Integer employee_id;

    //项目id
    private Integer item_id;

    //项目值
    private Double value;

    //工资状态，0计算，1暂存待发，2已发放；已发放的不可再修改
    private Integer state;

    //日期
    private Date date;

}
