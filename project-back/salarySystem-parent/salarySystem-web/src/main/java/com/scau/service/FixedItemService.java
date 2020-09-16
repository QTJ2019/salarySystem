package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.FixedItem;


public interface FixedItemService extends IService<FixedItem> {
    IPage<FixedItem> selectFixedItemByPage(Integer currentPage);
}
