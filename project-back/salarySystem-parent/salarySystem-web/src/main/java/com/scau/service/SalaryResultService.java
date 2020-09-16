package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.ImportItem;
import com.scau.entity.SalaryResult;

import java.util.List;
import java.util.Map;

/**
 * @Author: qtj
 * @Date: 2020/9/16 13:58
 * @Version
 */
public interface SalaryResultService  extends IService<SalaryResult> {
    IPage<SalaryResult> selectSalaryResultByPage(Integer currentPage);

    List<SalaryResult> querrySalaryForm(Map<String,Object> columnMap);
}
