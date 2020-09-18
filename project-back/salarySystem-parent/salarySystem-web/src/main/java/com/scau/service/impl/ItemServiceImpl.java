package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.Item;
import com.scau.mapper.ItemMapper;
import com.scau.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public IPage<Item> selectItemByPage(Integer currentPage) {
        IPage<Item> myItemPage=new Page<>(currentPage,10);
        IPage<Item> itemIPage=  itemMapper.selectPage(myItemPage,null);
        return itemIPage;
    }

    @Override
    public List<Item> selectByCondition(Item item) {
        return this.baseMapper.selectByCondition(item);
    }
}
