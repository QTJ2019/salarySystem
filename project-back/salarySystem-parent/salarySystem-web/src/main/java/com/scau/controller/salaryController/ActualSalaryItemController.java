package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.ActualSalaryItem;
import com.scau.service.ActualSalaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary/actualSalaryItem")
public class ActualSalaryItemController {
    @Autowired
    private ActualSalaryItemService actualSalaryItemService;

    @GetMapping("/findActualSalaryItemByPage/{currentPage}")
    public IPage<ActualSalaryItem> findActualSalaryItemByPage(@PathVariable("currentPage") Integer currentPage)
    {
        IPage<ActualSalaryItem> ActuallySalaryItemIPage=   actualSalaryItemService.selectActualSalaryItemByPage(currentPage);
        System.out.print("这是获取页面的数据"+ActuallySalaryItemIPage);
        return ActuallySalaryItemIPage;
    }

    @PostMapping(value = "/selectByCondition")
    public Result selectByCondition(@RequestBody ActualSalaryItem actualSalaryItem){
        Result result =null;
        List<ActualSalaryItem> actualSalaryItems = actualSalaryItemService.selectByCondition(actualSalaryItem);
        if(actualSalaryItems.size()==0){
//            System.out.println(actualSalaryItem);
            result=Result.ok();
            result= result.data("The search result is empty!",null);
        }else{
            result =Result.ok();
            result=result.data("data", actualSalaryItems);
        }
        return result;

    }

}
