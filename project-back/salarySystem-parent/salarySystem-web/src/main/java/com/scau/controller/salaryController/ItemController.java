package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.Item;
import com.scau.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/findItemByPage/{currentPage}")
    public IPage<Item> findItemByPage(@PathVariable("currentPage") Integer currentPage)
    {
        IPage<Item> ItemIPage=   itemService.selectItemByPage(currentPage);
        System.out.print("这是获取页面的数据"+ItemIPage.getRecords().get(0));
        return ItemIPage;
    }

    @PostMapping(value = "/selectByCondition")
    public Result selectByCondition(@RequestBody Item item){
        Result result;
        List<Item> items = itemService.selectByCondition(item);

        if(items.size()==0){
//            System.out.println(item);
            result= Result.ok();
            result= result.data("The search result is empty!",null);
            System.out.println(item);
        }else{
            result = Result.ok();
            result=result.data("data",items);
        }
        return result;

    }

}
