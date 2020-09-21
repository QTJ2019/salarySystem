package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.mapper.FixedItemMapper;
import com.scau.service.FixedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<FixedItem> selectAll() {
        return this.baseMapper.selectAll();
    }

    @Override
    public List<FixedItem> selectByEmployeeId(Integer id) {
        return this.baseMapper.selectByEmployeeId(id);
    }

    @Override
    public List<FixedItem> selectByCondition(FixedItem fixedItem) {
        return this.baseMapper.selectByCondition(fixedItem);
    }

//    @Override
//    public List<FixedItem> selectBy(FixedItem fixedItem, Limitation limitation) {
//        return this.baseMapper.selectBy(fixedItem,limitation);
//    }

//    @Override
//    public List<FixedItem> selectBy(Integer employeeId, String employeeName, String departmentName, String stationName, String itemName, Date startDate, Date endDate) {
//        return this.baseMapper.selectBy(employeeId,employeeName,departmentName,stationName,itemName,startDate,endDate);
//    }




}
