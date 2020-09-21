package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.FixedItem;

import java.util.List;


public interface FixedItemService extends IService<FixedItem> {
    IPage<FixedItem> selectFixedItemByPage(Integer currentPage);
    List<FixedItem> selectAll();
    List<FixedItem> selectByEmployeeId(Integer id);
//    List<FixedItem> selectBy(Integer employeeId, String employeeName , String departmentName, String stationName, String itemName, Date startDate, Date endDate);

    //    List<FixedItem> selectBy(FixedItem fixedItem,
    //                             Limitation limitation);

    List<FixedItem> selectByCondition(FixedItem fixedItem);
}
