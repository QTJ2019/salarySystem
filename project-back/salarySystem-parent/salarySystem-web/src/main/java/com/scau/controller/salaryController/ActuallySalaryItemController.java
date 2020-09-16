package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.entity.ActuallySalaryItem;
import com.scau.service.ActuallySalaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actuallySalaryItem")
public class ActuallySalaryItemController {
    @Autowired
    private ActuallySalaryItemService actuallySalaryItemService;

    @GetMapping("/findActuallySalaryItemByPage/{currentPage}")
    public IPage<ActuallySalaryItem> findActuallySalaryItemByPage(@PathVariable("currentPage") Integer currentPage)
    {
        IPage<ActuallySalaryItem> ActuallySalaryItemIPage=   actuallySalaryItemService.selectActuallySalaryItemByPage(currentPage);
        System.out.print("这是获取页面的数据"+ActuallySalaryItemIPage);
        return ActuallySalaryItemIPage;
    }
}
