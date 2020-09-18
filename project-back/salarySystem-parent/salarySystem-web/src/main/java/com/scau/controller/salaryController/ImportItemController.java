package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.FixedItem;
import com.scau.entity.ImportItem;
import com.scau.service.ImportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary/importItem")
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

    @PostMapping(value = "/selectByCondition")
    public Result selectByCondition(@RequestBody ImportItem importItem){
        Result result =null;
        List<ImportItem> importItems = importItemService.selectByCondition(importItem);

        if(importItems.size()==0){
//            System.out.println(importItem);
            result=Result.ok();
            result= result.data("The search result is empty!",null);
            System.out.println(importItem.getEndDate());
        }else{
            result =Result.ok();
            result=result.data("data",importItems);
            System.out.println(importItem.getEndDate());
            System.out.println(importItems.get(0).getDate());
        }
        return result;

    }
}
