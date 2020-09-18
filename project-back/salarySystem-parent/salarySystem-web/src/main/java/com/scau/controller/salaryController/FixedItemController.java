package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.*;
import com.scau.entity.FixedItem;

import com.scau.entity.Limitation;
import com.scau.service.FixedItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/salary/fixedItem")
public class FixedItemController {
    @Autowired
    private FixedItemService fixedItemService;


    @GetMapping(value = "/findFixedItemByPage/{currentPage}")
    public Result findFixedItemByPage(@PathVariable(value = "currentPage") Integer currentPage)
    {
        IPage<FixedItem> FixedItemIPage=   fixedItemService.selectFixedItemByPage(currentPage);
//        System.out.print("这是获取页面的数据"+FixedItemIPage);
        Result result= Result.ok();
        result = result.data("data",FixedItemIPage);
        return result;
    }

    @GetMapping(value = "/selectAllFIxedItem")
    public Result selectAll(){
        Result result = Result.ok();
        result = result.data("data",fixedItemService.selectAll());
        return result;
    }

//    @GetMapping(value = "/selectAllFixedItemByPage/{page}/{size}")
//    public Result selectAllFixedItemByPage(@PathVariable Integer page, @PathVariable Integer size){
//            Result result = Result.ok();
//
//            return  result;
//    }
    @GetMapping(value = "/selectFixedItemByEmployeeId/{id}")
    public Result selectByEmplyeeId(@PathVariable Integer id){
        Result result ;
        List<FixedItem> list = fixedItemService.selectByEmployeeId(id);
        if(list.size()==0){
            result=Result.error();
        }else{
            result =Result.ok();
            result =result.data("data",list);
        }
        return  result;
    }

//    @PostMapping(value = "/selectBy")
//    public Result selectBy(@RequestParam(value = "employeeId" ,required = false) Integer employeeId,
//                           @RequestParam(value = "employeeName" ,required = false) String employeeName,
//                           @RequestParam(value = "departmentName" ,required = false) String departmentName,
//                           @RequestParam(value = "stationName" ,required = false) String stationName,
//                           @RequestParam(value = "itemName" ,required = false) String itemName,
//                           @RequestParam(value = "startDate", required = false) Date startDate,
//                           @RequestParam(value = "endDate", required = false) Date endDate){
//        Result result;
//        List<FixedItem> fixedItems =fixedItemService.selectBy(employeeId,employeeName,departmentName,stationName,itemName,startDate,endDate);
//        if(fixedItems.size()==0){
//            result=Result.error();
//        }else{
//            result=Result.ok();
//            result=result.data("data",fixedItems);
//        }
//        return result;
//
//    }


//    public Result selectByMap(@RequestBody FixedItem fixedItem ,
//                              @Param("minValue") Double minValue,
//                              @Param("maxValue") Double maxValue,
//                              @Param("startDate") Date startDate,
//                              @Param("endDate") Date endDate){
//        Result result =null;
//        Limitation limitation =new Limitation();
//        limitation.setMinValue(minValue);
//        limitation.setMaxValue(maxValue);
//        limitation.setStartDate(startDate);
//        limitation.setEndDate(endDate);
    @PostMapping(value = "/selectByCondition")
    public Result selectByCondition(@RequestBody FixedItem fixedItem){
        Result result =null;
        List<FixedItem> fixedItems = fixedItemService.selectByCondition(fixedItem);
        if(fixedItems.size()==0){
//            System.out.println(fixedItem);
            result=Result.ok();
            result= result.data("The search result is empty!",null);
        }else{
            result =Result.ok();
            result=result.data("data",fixedItems);
        }
        return result;

    }
}
