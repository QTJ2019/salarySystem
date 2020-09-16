package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.entity.ImportItem;
import com.scau.service.ImportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/importItem")
public class ImportItemController {
    @Autowired
    private ImportItemService importItemService;

    @GetMapping("/findImportItemByPage/{currentPage}")
    public IPage<ImportItem> findFixedItemByPage(@PathVariable("currentPage") Integer currentPage)
    {
        IPage<ImportItem> ImportItemIPage=   importItemService.selectImportItemByPage(currentPage);
        System.out.print("这是获取页面的数据"+ImportItemIPage);
        return ImportItemIPage;
    }
}
