package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @Author: qtj
 * @Date: 2020/9/16 11:56
 * @Version
 */
@Data
@TableName("salary_result")
public class SalaryResult {
    @TableId(type = IdType.AUTO)
    //工资结果id
    private Integer id;

    //员工id
    private Integer employeeId;

    //实发工资
    private Double actualSalary;

    //基本工资
    private Double basicSalary;

    //高温补贴
    private Double hotSalary;

    //采暖补贴
    private Double coldSalary;

    //交通补贴
    private Double trafficSalary;

    //住房补贴
    private Double houseSalary;

    //迟到次数
    private Integer lateCount;

    //迟到扣款
    private  Double lateFine;

    //病假次数
    private Integer sickLeaveCount;

    //病假扣款
    private  Integer sickLeaveFine;

    //事假次数
    private  Integer eventLeaveCount;

    //事假扣款
    private Double eventLeaveFine;

    //加班次数
    private  Integer extraWorkCount;

    //加班工资
    private  Double extraWorkSalary;

    //单位缴纳养老保险
    private Double companyPaidOldIsurance;

    //个人缴纳养老保险
    private Double employeePaidOldIsurance;

    //单位缴纳医疗保险
    private Double companyPaidMedicalIsurance;

    //个人缴纳医疗保险
    private Double employeePaidMedicalIsurance;

    //单位缴纳失业保险
    private Double companyPaidUnemploymentIsurance;

    //个人缴纳失业保险
    private Double employeePaidUnemploymentIsurance;

    //单位缴纳工伤保险
    private Double companyPaidInjuryIsurance;

    //单位缴纳生育保险
    private Double companyPaidPregnantIsurance;

    //单位缴纳住房公积金
    private Double compamyPaidHousefund;

    //个人缴纳住房公积金
    private Double employee_paid_housefund;

    //个人所得税
    private Double tax;

    //工资所属时间
    private Data date;




}
