package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.CalculateItem;


public interface CalculateItemService extends IService<CalculateItem> {
    IPage<CalculateItem> selectCalculateItemByPage(Integer currentPage);
}
