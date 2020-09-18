package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.ActualSalaryItem;

import java.util.List;


public interface ActualSalaryItemService extends IService<ActualSalaryItem> {
    IPage<ActualSalaryItem> selectActualSalaryItemByPage(Integer currentPage);

    List<ActualSalaryItem> selectByCondition(ActualSalaryItem actualSalaryItem);
}
