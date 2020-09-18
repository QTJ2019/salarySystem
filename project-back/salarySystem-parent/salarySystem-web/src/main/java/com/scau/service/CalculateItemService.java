package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.CalculateItem;
import com.scau.entity.FixedItem;

import java.util.List;


public interface CalculateItemService extends IService<CalculateItem> {
    IPage<CalculateItem> selectCalculateItemByPage(Integer currentPage);

    List<CalculateItem> selectByCondition(CalculateItem calculateItem);
}
