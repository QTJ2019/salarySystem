package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.mapper.ActualSalaryItemMapper;
import com.scau.service.ActualSalaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualSalaryItemServiceImpl extends ServiceImpl<ActualSalaryItemMapper, ActualSalaryItem> implements ActualSalaryItemService {

    @Autowired
    private ActualSalaryItemMapper actualSalaryItemMapper;

    @Override
    public IPage<ActualSalaryItem> selectActualSalaryItemByPage(Integer currentPage) {
        IPage<ActualSalaryItem> myActuallySalaryItemPage=new Page<>(currentPage,3);
        IPage<ActualSalaryItem> actualSalaryItemIPage=  actualSalaryItemMapper.selectPage(myActuallySalaryItemPage,null);
        return actualSalaryItemIPage;
    }

    @Override
    public List<ActualSalaryItem> selectByCondition(ActualSalaryItem actualSalaryItem) {
        return this.baseMapper.selectByCondition(actualSalaryItem);
    }
}
