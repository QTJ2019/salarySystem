package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.SalaryResult;
import com.scau.mapper.SalaryResultMapper;
import com.scau.service.SalaryResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: qtj
 * @Date: 2020/9/16 14:44
 * @Version
 */
@Service
public class SalaryResultServiceImpl extends ServiceImpl<SalaryResultMapper, SalaryResult> implements SalaryResultService {
    @Autowired
    private SalaryResultMapper salaryResultMapper;

    @Override
    public IPage<SalaryResult> selectSalaryResultByPage(Integer currentPage) {
        return null;
    }

    @Override
    public List<SalaryResult> querrySalaryForm(String deptName, Date startDate, Date endDate, Integer employeeId) {
        return salaryResultMapper.querySalaryForm(deptName,startDate,endDate,employeeId);
    }
}
