package com.scau.system.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.system.item.entity.Item;
import com.scau.system.item.mapper.ItemMapper;
import com.scau.system.item.entity.Item;
import com.scau.system.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper , Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public IPage<Item> selectItemByPage(Integer currentPage) {
        IPage<Item> ItemPage=new Page<>(currentPage,3);
        IPage<Item> ItemIPage=  itemMapper.selectPage(ItemPage,null);
        return ItemIPage;
    }
}
