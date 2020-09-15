package com.scau.system.item.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.system.item.entity.Item;
import com.scau.system.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/findItemByPage/{currentPage}")
    public IPage<Item> findItemByPage(@PathVariable("currentPage") Integer currentPage)
    {
        IPage<Item> ItemIPage=   itemService.selectItemByPage(currentPage);
        System.out.print("这是获取页面的数据"+ItemIPage);
        return ItemIPage;
    }
}
