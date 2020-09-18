package com.scau.entity;

import lombok.Data;

import java.util.Date;

/**
 * 部门统计报表
 * @Author: qtj
 * @Date: 2020/9/17 15:12
 * @Version
 */
@Data
public class DeptStatistic {
    //部门编号
    private Integer deptId;

    //部门名称
    private String deptName;

    //总基本工资
    private Double sumBasicSalary;

    //平均基本工资
    private Double avgBasicSalary;

    //最低基本工资
    private Double minBasicSalary;

    //最高基本工资
    private Double maxBasicSalary;

}
