package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.FixedItem;
import com.scau.mapper.FixedItemMapper;
import com.scau.service.FixedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FixedItemServiceImpl extends ServiceImpl<FixedItemMapper, FixedItem> implements FixedItemService {

    @Autowired
    private FixedItemMapper fixedItemMapper;

    @Override
    public IPage<FixedItem> selectFixedItemByPage(Integer currentPage) {
        IPage<FixedItem> myFixedItemPage=new Page<>(currentPage,3);
        IPage<FixedItem> fixedItemIPage=  fixedItemMapper.selectPage(myFixedItemPage,null);
        return fixedItemIPage;
    }
}
