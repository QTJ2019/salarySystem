package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.Item;

public interface ItemService extends IService<Item> {
    IPage<Item> selectItemByPage(Integer currentPage);
}
