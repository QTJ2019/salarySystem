package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.CalculateItem;
import com.scau.mapper.CalculateItemMapper;
import com.scau.service.CalculateItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateItemServiceImpl extends ServiceImpl<CalculateItemMapper, CalculateItem> implements CalculateItemService {

    @Autowired
    private CalculateItemMapper calculateItemMapper;

    @Override
    public IPage<CalculateItem> selectCalculateItemByPage(Integer currentPage) {
        IPage<CalculateItem> myCalculateItemPage=new Page<>(currentPage,3);
        IPage<CalculateItem> calculateItemIPage=  calculateItemMapper.selectPage(myCalculateItemPage,null);
        return calculateItemIPage;
    }

    @Override
    public List<CalculateItem> selectByCondition(CalculateItem calculateItem) {
        return this.baseMapper.selectByCondition(calculateItem);
    }
}
