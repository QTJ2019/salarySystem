package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.ActuallySalaryItem;


public interface ActuallySalaryItemService extends IService<ActuallySalaryItem> {
    IPage<ActuallySalaryItem> selectActuallySalaryItemByPage(Integer currentPage);
}
