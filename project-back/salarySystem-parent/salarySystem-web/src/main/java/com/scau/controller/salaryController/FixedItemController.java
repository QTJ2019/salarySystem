package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.entity.FixedItem;
import com.scau.service.FixedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fixedItem")
public class FixedItemController {
    @Autowired
    private FixedItemService fixedItemService;

    @GetMapping("/findFixedItemByPage/{currentPage}")
    public IPage<FixedItem> findFixedItemByPage(@PathVariable("currentPage") Integer currentPage)
    {
        IPage<FixedItem> FixedItemIPage=   fixedItemService.selectFixedItemByPage(currentPage);
        System.out.print("这是获取页面的数据"+FixedItemIPage);
        return FixedItemIPage;
    }
}
