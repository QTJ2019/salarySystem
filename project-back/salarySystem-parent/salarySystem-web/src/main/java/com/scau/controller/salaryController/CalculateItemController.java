package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.entity.CalculateItem;
import com.scau.service.CalculateItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculateItem")
public class CalculateItemController {
    @Autowired
    private CalculateItemService calculateItemService;

    @GetMapping("/findCalculateItemByPage/{currentPage}")
    public IPage<CalculateItem> findCalculateItemByPage(@PathVariable("currentPage") Integer currentPage)
    {
        IPage<CalculateItem> CalculateItemIPage=   calculateItemService.selectCalculateItemByPage(currentPage);
        System.out.print("这是获取页面的数据"+CalculateItemIPage);
        return CalculateItemIPage;
    }
}
