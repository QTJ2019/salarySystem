package com.scau.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @ExcelIgnore
    private Integer id;

    //员工id
    @ExcelProperty("员工编号")
    private Integer employeeId;

    //实发工资
    @ExcelProperty("实发工资")
    private Double actualSalary;

    //基本工资
    @ExcelProperty("基本工资")
    private Double basicSalary;

    //高温补贴
    @ExcelProperty("高温补贴")
    private Double hotSalary;

    //采暖补贴
    @ExcelProperty("采暖补贴")
    private Double coldSalary;

    //交通补贴
    @ExcelProperty("交通补贴")
    private Double trafficSalary;

    //住房补贴
    @ExcelProperty("住房补贴")
    private Double houseSalary;

    //迟到次数
    @ExcelProperty("迟到次数")
    private Integer lateCount;

    //迟到扣款
    @ExcelProperty("迟到扣款")
    private  Double lateFine;

    //病假次数
    @ExcelProperty("病假次数")
    private Integer sickLeaveCount;

    //病假扣款
    @ExcelProperty("病假扣款")
    private  Double sickLeaveFine;

    //事假次数
    @ExcelProperty("事假次数")
    private  Integer eventLeaveCount;

    //事假扣款
    @ExcelProperty("事假扣款")
    private Double eventLeaveFine;

    //加班次数
    @ExcelProperty("加班次数")
    private  Integer extraWorkCount;

    //加班工资
    @ExcelProperty("加班工资")
    private  Double extraWorkSalary;

    //单位缴纳养老保险
    @ExcelProperty("单位加纳养老保险")
    private Double companyPaidOldIsurance;

    //个人缴纳养老保险
    @ExcelProperty("个人缴纳养老保险")
    private Double employeePaidOldIsurance;

    //单位缴纳医疗保险
    @ExcelProperty("单位缴纳医疗保险")
    private Double companyPaidMedicalIsurance;

    //个人缴纳医疗保险
    @ExcelProperty("个人缴纳医疗保险")
    private Double employeePaidMedicalIsurance;

    //单位缴纳失业保险
    @ExcelProperty("单位缴纳失业保险")
    private Double companyPaidUnemploymentIsurance;

    //个人缴纳失业保险
    @ExcelProperty("个人缴纳失业保险")
    private Double employeePaidUnemploymentIsurance;

    //单位缴纳工伤保险
    @ExcelProperty("单位缴纳工伤保险")
    private Double companyPaidInjuryIsurance;

    //单位缴纳生育保险
    @ExcelProperty("单位缴纳生育保险")
    private Double companyPaidPregnantIsurance;

    //单位缴纳住房公积金
    @ExcelProperty("单位加纳住房公积金")
    private Double compamyPaidHousefund;

    //个人缴纳住房公积金
    @ExcelProperty("个人缴纳住房公积金")
    private Double employeePaidHousefund;

    //个人所得税
    @ExcelProperty("个人所得税")
    private Double tax;

    //工资所属时间
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    @ExcelProperty("时间")
    private Date date;




}
