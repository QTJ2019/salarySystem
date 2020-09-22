package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.Employee;
import com.scau.entity.FixedItem;
import com.scau.entity.Item;
import com.scau.service.EmployeeService;
import com.scau.service.FixedItemService;
import com.scau.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/salary/fixedItem")
public class FixedItemController {
    @Autowired
    private FixedItemService fixedItemService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/findFixedItemByPage/{currentPage}")
    public Result findFixedItemByPage(@PathVariable(value = "currentPage") Integer currentPage)
    {
        IPage<FixedItem> FixedItemIPage=   fixedItemService.selectFixedItemByPage(currentPage);
//        System.out.print("这是获取页面的数据"+FixedItemIPage);
        Result result= Result.ok();
        result = result.data("data",FixedItemIPage);
        return result;
    }

    @GetMapping(value = "/selectAllFixedItem")
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
            result= Result.error();
        }else{
            result = Result.ok();
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

    /**
     * 不定条件查询
     * @param fixedItem
     * @return
     */
    @PostMapping(value = "/selectByCondition")
    public Result selectByCondition(@RequestBody FixedItem fixedItem){
        Result result;
        List<FixedItem> fixedItems = fixedItemService.selectByCondition(fixedItem);
        if(fixedItems.size()==0){
//            System.out.println(fixedItem);
            result= Result.ok();
            result= result.data("The search result is empty!",null);
        }else{
            result = Result.ok();
            result=result.data("data",fixedItems);
        }
        return result;

    }

    /**
     * 通过员工id，项目id，具体值(非必须)，日期插入一条固定项目记录
     * @param fixedItem
     * @return
     */
    @PostMapping(value = "/addRecord")
    public Result addRecord(@RequestBody FixedItem fixedItem){
        Result result = null;
        if(fixedItem==null){
            result =result.error();
            result =result.message("null object!");
        }else if(fixedItem.getEmployeeId()==null){
            result =result.error();
            result =result.message("null employeeId!");
        }else if(fixedItem.getEmployeeId()<=0){
            result =result.error();
            result =result.message("illegal employeeId!");
        }else if(!employeeIsExist(fixedItem.getEmployeeId())){
            result =result.error();
            result =result.message("the employeeId Not Exist!");
        }else if(fixedItem.getItemId()==null){
            result =result.error();
            result =result.message("null itemId!");
        }else if(fixedItem.getItemId()<=0){
            result =result.error();
            result =result.message("illegal itemId!");
        }else if(!itemIsExist(fixedItem.getItemId())){
            result =result.error();
            result =result.message("the itemId Not Exist!");
        }else if(fixedItem.getDate()==null){
            result =result.error();
            result =result.message("null date!");
        }else{
            boolean isSaved = fixedItemService.save(fixedItem);
            if(isSaved){
                result= Result.ok();
            }else{
                result=result.error();
                result =result.message("insert ERROR!");
            }
        }
        return result;
    }

    @PostMapping("/changeRecord")
    public Result changeRecord(@RequestBody FixedItem fixedItem){
        Result result;
        boolean isChanged = fixedItemService.updateById(fixedItem);
        if(isChanged){
            result = Result.ok();
        }else{
            result = Result.error();
        }
        return result;
    }

    @PostMapping("/deleteRecord")
    public Result deleteRecord(@RequestBody FixedItem fixedItem){
        Result result;
        boolean isDeleted =fixedItemService.removeById(fixedItem.getId());
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
