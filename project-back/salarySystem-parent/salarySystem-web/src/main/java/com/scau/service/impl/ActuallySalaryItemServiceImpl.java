package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.ActuallySalaryItem;
import com.scau.mapper.ActuallySalaryItemMapper;
import com.scau.service.ActuallySalaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActuallySalaryItemServiceImpl extends ServiceImpl<ActuallySalaryItemMapper, ActuallySalaryItem> implements ActuallySalaryItemService {

    @Autowired
    private ActuallySalaryItemMapper actuallySalaryItemMapper;

    @Override
    public IPage<ActuallySalaryItem> selectActuallySalaryItemByPage(Integer currentPage) {
        IPage<ActuallySalaryItem> myActuallySalaryItemPage=new Page<>(currentPage,3);
        IPage<ActuallySalaryItem> actuallySalaryItemIPage=  actuallySalaryItemMapper.selectPage(myActuallySalaryItemPage,null);
        return actuallySalaryItemIPage;
    }
}
