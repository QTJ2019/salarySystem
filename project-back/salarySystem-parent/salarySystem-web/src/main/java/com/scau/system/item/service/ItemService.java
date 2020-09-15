package com.scau.system.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.system.item.entity.Item;
import com.scau.system.item.entity.Item;

public interface ItemService extends IService<Item> {
    public IPage<Item> selectItemByPage(Integer currentPage);
}
