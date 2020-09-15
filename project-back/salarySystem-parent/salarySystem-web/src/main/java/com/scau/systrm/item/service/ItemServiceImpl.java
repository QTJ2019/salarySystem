package com.scau.systrm.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.systrm.item.entity.Item;
import com.scau.systrm.item.mapper.ItemMapper;
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
