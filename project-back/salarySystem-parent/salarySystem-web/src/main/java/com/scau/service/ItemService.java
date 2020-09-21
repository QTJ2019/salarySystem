package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.Item;

import java.util.List;

public interface ItemService extends IService<Item> {
    IPage<Item> selectItemByPage(Integer currentPage);

    List<Item> selectByCondition(Item item);
}
