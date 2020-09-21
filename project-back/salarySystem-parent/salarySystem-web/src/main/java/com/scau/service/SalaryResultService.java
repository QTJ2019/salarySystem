package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.CompanyStatistic;
import com.scau.entity.DeptStatistic;
import com.scau.entity.SalaryResult;

import java.util.Date;
import java.util.List;

/**
 * @Author: qtj
 * @Date: 2020/9/16 13:58
 * @Version
 */
public interface SalaryResultService  extends IService<SalaryResult> {
    IPage<SalaryResult> selectSalaryResultByPage(Integer currentPage);

    List<SalaryResult> querrySalaryForm(String deptName, Date startDate, Date endDate, Integer employeeId);

    List<DeptStatistic> queryDeptStatistic(String deptName, Date startMonth,  Date endMonth);

    CompanyStatistic queryCompanyStatistic(Date startMonth, Date endMonth);
}
