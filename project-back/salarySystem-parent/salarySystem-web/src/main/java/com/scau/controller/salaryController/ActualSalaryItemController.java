package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.ActualSalaryItem;
import com.scau.entity.Employee;
import com.scau.entity.Item;
import com.scau.service.ActualSalaryItemService;
import com.scau.service.EmployeeService;
import com.scau.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary/actualSalaryItem")
public class ActualSalaryItemController {
    @Autowired
    private ActualSalaryItemService actualSalaryItemService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ItemService itemService;

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
            result= Result.ok();
            result= result.data("The search result is empty!",null);
        }else{
            result = Result.ok();
            result=result.data("data", actualSalaryItems);
        }
        return result;

    }

    /**
     * 通过员工id，项目id，具体值(非必须)，日期插入一条固定项目记录
     * @param actualSalaryItem
     * @return
     */
    @PostMapping(value = "/addRecord")
    public Result addRecord(@RequestBody ActualSalaryItem actualSalaryItem){
        Result result = null;
        if(actualSalaryItem==null){
            result = Result.error();
            result =result.message("null object!");
        }else if(actualSalaryItem.getEmployeeId()==null){
            result = Result.error();
            result =result.message("null employeeId!");
        }else if(actualSalaryItem.getEmployeeId()<=0){
            result = Result.error();
            result =result.message("illegal employeeId!");
        }else if(!employeeIsExist(actualSalaryItem.getEmployeeId())){
            result = Result.error();
            result =result.message("the employeeId Not Exist!");
        }else if(actualSalaryItem.getItemId()==null){
            result = Result.error();
            result =result.message("null itemId!");
        }else if(actualSalaryItem.getItemId()<=0){
            result = Result.error();
            result =result.message("illegal itemId!");
        }else if(!itemIsExist(actualSalaryItem.getItemId())){
            result = Result.error();
            result =result.message("the itemId Not Exist!");
        }else if(actualSalaryItem.getDate()==null){
            result = Result.error();
            result =result.message("null date!");
        }else{
            boolean isSaved = actualSalaryItemService.save(actualSalaryItem);
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
    public Result changeRecord(@RequestBody ActualSalaryItem actualSalaryItem){
        Result result;
        boolean isChanged = actualSalaryItemService.updateById(actualSalaryItem);
        if(isChanged){
            result = Result.ok();
        }else{
            result = Result.error();
        }
        return result;
    }

    @PostMapping("/deleteRecord")
    public Result deleteRecord(@RequestBody ActualSalaryItem actualSalaryItem){
        Result result;
        boolean isDeleted =actualSalaryItemService.removeById(actualSalaryItem.getId());
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
