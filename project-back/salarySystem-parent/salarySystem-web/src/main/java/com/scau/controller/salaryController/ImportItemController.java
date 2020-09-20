package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.Employee;
import com.scau.entity.ImportItem;
import com.scau.entity.Item;
import com.scau.service.EmployeeService;
import com.scau.service.ImportItemService;
import com.scau.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary/importItem")
public class ImportItemController {
    @Autowired
    private ImportItemService importItemService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/findImportItemByPage/{currentPage}")
    public IPage<ImportItem> findImportItemByPage(@PathVariable("currentPage") Integer currentPage)
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
            result= Result.ok();
            result= result.data("The search result is empty!",null);
            System.out.println(importItem.getEndDate());
        }else{
            result = Result.ok();
            result=result.data("data",importItems);
            System.out.println(importItem.getEndDate());
            System.out.println(importItems.get(0).getDate());
        }
        return result;

    }

    @PostMapping(value = "/addRecord")
    public Result addRecord(@RequestBody ImportItem importItem){
        Result result = null;
        if(importItem==null){
            result = Result.error();
            result =result.message("null object!");
        }else if(importItem.getEmployeeId()==null){
            result = Result.error();
            result =result.message("null employeeId!");
        }else if(importItem.getEmployeeId()<=0){
            result = Result.error();
            result =result.message("illegal employeeId!");
        }else if(!employeeIsExist(importItem.getEmployeeId())){
            result = Result.error();
            result =result.message("the employeeId Not Exist!");
        }else if(importItem.getItemId()==null){
            result = Result.error();
            result =result.message("null itemId!");
        }else if(importItem.getItemId()<=0){
            result = Result.error();
            result =result.message("illegal itemId!");
        }else if(!itemIsExist(importItem.getItemId())){
            result = Result.error();
            result =result.message("the itemId Not Exist!");
        }else if(importItem.getDate()==null){
            result = Result.error();
            result =result.message("null date!");
        }else{
            boolean isSaved = importItemService.save(importItem);
            if(isSaved){
                result= Result.ok();
            }else{
                result= Result.error();
                result =result.message("insert ERROR!");
            }
        }
        return result;
    }

    @PostMapping("/changeRecord")
    public Result changeRecord(@RequestBody ImportItem importItem){
        Result result;
        boolean isChanged = importItemService.updateById(importItem);
        if(isChanged){
            result = Result.ok();
        }else{
            result = Result.error();
        }
        return result;
    }

    @PostMapping("/deleteRecord")
    public Result deleteRecord(@RequestBody ImportItem importItem){
        Result result;
        boolean isDeleted =importItemService.removeById(importItem.getId());
        if(isDeleted){
            result = Result.ok();
        }else {
            result= Result.error();
        }
        return result;
    }

//    public Result

    /**
     * employee存在性
     * @param employeeId
     * @return
     */
    public boolean employeeIsExist(Integer employeeId){
        boolean isExist=false;
        QueryWrapper<Employee> wrapper=new QueryWrapper<>();
        wrapper.eq("id",employeeId);
        Employee employee=employeeService.getOne(wrapper);
        if(employee==null){
            isExist=false;
        }else{
            isExist=true;
        }
        return isExist;
    }

    /**
     * Item存在性
     * @param itemId
     * @return
     */
    public boolean itemIsExist(Integer itemId){
        boolean isExist=false;
        QueryWrapper<Item> wrapper=new QueryWrapper<>();
        wrapper.eq("id",itemId);
        Item item= itemService.getOne(wrapper);
        if(item==null){
            isExist=false;
        }else{
            isExist=true;
        }
        return isExist;
    }
}
