package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.ImportItem;
import com.scau.entity.SalaryResult;

/**
 * @Author: qtj
 * @Date: 2020/9/16 13:58
 * @Version
 */
public interface SalaryResultService  extends IService<SalaryResult> {
    IPage<SalaryResult> selectSalaryResultByPage(Integer currentPage);
}
