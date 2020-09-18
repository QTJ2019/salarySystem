package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.CalculateItem;
import com.scau.entity.FixedItem;
import com.scau.service.CalculateItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary/calculateItem")
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

    @PostMapping(value = "/selectByCondition")
    public Result selectByCondition(@RequestBody CalculateItem calculateItem){
        Result result =null;
        List<CalculateItem> calculateItems = calculateItemService.selectByCondition(calculateItem);
        if(calculateItems.size()==0){
//            System.out.println(fixedItem);
            result=Result.ok();
            result= result.data("The search result is empty!",null);
        }else{
            result =Result.ok();
            result=result.data("data",calculateItems);
        }
        return result;

    }

}
