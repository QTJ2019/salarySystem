package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.*;
import com.scau.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
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

    @Autowired
    private FixedItemService fixedItemService;

    @Autowired
    private ImportItemService importItemService;

    @Autowired
    private CalculateItemService calculateItemService;

    @Autowired
    private SalaryResultService salaryResultService;

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

    /**
     * 工资结算
     * @param actualSalaryItem
     * @return
     */
    @PostMapping("/settleSalary")
    public Result settleSalary(@RequestBody ActualSalaryItem actualSalaryItem){
        Result result=null;
        if(!isAbleToSettleSalary(actualSalaryItem)){
            result = Result.error();
            result =result.message("Unable to settleSlary!");
            return result;
        }


        List<Result> resultSet =new ArrayList<Result>();

        List<FixedItem>  fixedItems  =new ArrayList<FixedItem>();

        resultSet.add(settleFixedItemSalary(fixedItems,actualSalaryItem));

        if(!resultSet.get(0).isSuccess()){
            result = Result.error();
            result =result.data("settleFixedItemSalary ERROR!",resultSet);
            return result;
        }

        List<CalculateItem> calculateItems = new ArrayList<CalculateItem>();

        resultSet.add(settleCalculateItemSalary(calculateItems,actualSalaryItem));

        if(!resultSet.get(1).isSuccess()){
            result = Result.error();
            result =result.data("settleCalculateItemSalary ERROR!",resultSet);
            return result;
        }

        Double salaryBeforeTax=Double.parseDouble(resultSet.get(0).getData().get("settleFixedItemSalary").toString())
                +Double.parseDouble(resultSet.get(1).getData().get("settleCalculateItemSalary").toString());

        List<CalculateItem> tax =new ArrayList<CalculateItem>();

        resultSet.add(settleTax(salaryBeforeTax,tax,actualSalaryItem));

        if(!resultSet.get(2).isSuccess()){
            result = Result.error();
            result =result.data("settleTax ERROR!",resultSet);
            return result;
        }

        //TODO :add all salary
        Double finalSalary = Double.parseDouble(resultSet.get(0).getData().get("settleFixedItemSalary").toString())
                +Double.parseDouble(resultSet.get(1).getData().get("settleCalculateItemSalary").toString())
                +Double.parseDouble(resultSet.get(2).getData().get("settleTax").toString());


        actualSalaryItem.setStartDate(actualSalaryItem.getDate());
        actualSalaryItem.setEndDate(actualSalaryItem.getDate());
        int isExistSettleSalary =isExistActualSalaryItem(actualSalaryItem);

        actualSalaryItem.setItemId(13);
        actualSalaryItem.setValue(finalSalary);
        actualSalaryItem.setState(1);

        boolean isSettleSalaryOk;
        if(isExistSettleSalary==-1){
            isSettleSalaryOk=false;
        }else if(isExistSettleSalary==0){
            isSettleSalaryOk=actualSalaryItemService.save(actualSalaryItem);
        }else {
            actualSalaryItem.setId(isExistSettleSalary);
            isSettleSalaryOk=actualSalaryItemService.updateById(actualSalaryItem);
        }

        if(isSettleSalaryOk){
            Result finalSalaryResult = Result.ok();
            finalSalaryResult=finalSalaryResult.message("settleSalary successfully!");
            finalSalaryResult=finalSalaryResult.data("settleSalary",finalSalary);
            resultSet.add(finalSalaryResult);
        }else {
            result = Result.error();
            result=result.message("salarySettleERROR");
            result =result.data("data",resultSet);
            return result;
        }

        resultSet.add(markSettledItem(actualSalaryItem));

        if(!resultSet.get(4).isSuccess()){
            result = Result.error();
            result =result.data("mark ERROR!",resultSet);
            return result;
        }

        result = Result.ok();
        result =result.data("resultSet",resultSet);


        return result;
    }

    /**
     * 查看当前要生成的事发项目是否已存在，存在则update，否则insert
     * @param actualSalaryItem
     * @return
     */
    public int isExistActualSalaryItem(ActualSalaryItem actualSalaryItem){
        List<ActualSalaryItem> actualSalaryItems=actualSalaryItemService.selectByCondition(actualSalaryItem);
        if(actualSalaryItems.size()>1){
            return -1;
        } else if(actualSalaryItems.size()==0){
            return 0;
        }else {
            return actualSalaryItems.get(0).getId();
        }
    }

    public boolean isAbleToSettleSalary(ActualSalaryItem actualSalaryItem){
        actualSalaryItem.setStartDate(actualSalaryItem.getDate());
        actualSalaryItem.setEndDate(actualSalaryItem.getDate());
        List<ActualSalaryItem> actualSalaryItems=actualSalaryItemService.selectByCondition(actualSalaryItem);
        if(actualSalaryItems.size()>1){
            return false;
        } else if(actualSalaryItems.size()==0){
            return true;
        }else {
            if(actualSalaryItems.get(0).getState()==2){
                return false;
            }else {
                return true;
            }
        }
    }

    /**
     * 结算固定项目
     * @param actualSalaryItem
     * @return
     */
    public Result settleFixedItemSalary(List<FixedItem> fixedItems, ActualSalaryItem actualSalaryItem){
        Result result =null;
        FixedItem condition=new FixedItem();

        condition.setEmployeeId(actualSalaryItem.getEmployeeId());
        condition.setStartDate(actualSalaryItem.getDate());
        condition.setEndDate(actualSalaryItem.getDate());

         fixedItems = fixedItemService.selectByCondition(condition);

        if(!isExistBasicSalary(fixedItems)){
            result = Result.error();
            result =result.message("lack of basicSalary!");
            return result;
        }

        Double fixedItemSalary=0.0;
        for(FixedItem fixedItem:fixedItems){
            fixedItemSalary+=fixedItem.getEffective()*fixedItem.getValue();
        }

        result = Result.ok();
        result =result.message("settleFixedItemSalary successfully!");
        result =result.data("settleFixedItemSalary",fixedItemSalary);
        return result;
    }

    /**
     * 判断基本工资是否存在，无基本工资不进行结算
     * @param fixedItems
     * @return
     */
    public boolean isExistBasicSalary(List<FixedItem> fixedItems){
        if(fixedItems!=null && fixedItems.size()>0){
            for(FixedItem fixedItem : fixedItems){
                if(fixedItem.getItemId()==1){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 结算计算项目除个人所得税外的工资
     * @return
     */
    public Result settleCalculateItemSalary(List<CalculateItem> calculateItems, ActualSalaryItem actualSalaryItem){
        Result result;
        CalculateItem condition =new CalculateItem();

        condition.setEmployeeId(actualSalaryItem.getEmployeeId());
        condition.setStartDate(actualSalaryItem.getDate());
        condition.setEndDate(actualSalaryItem.getDate());

        calculateItems = calculateItemService.selectByCondition(condition);

        Double calculateItemSalary =0.0;
        for(CalculateItem calculateItem:calculateItems){
            //排除个人所得税
            if(calculateItem.getItemId()!=28){
                calculateItemSalary+=calculateItem.getEffective()*calculateItem.getValue();
            }
        }

        result = Result.ok();
        result =result.message("settleCalculateItemSalary successfully!");
        result =result.data("settleCalculateItemSalary",calculateItemSalary);

        return result;
    }

    /**
     * 结算个人所得税，无则insert，有则update
     * @param actualSalaryItem
     * @return
     */
    public Result settleTax(Double salaryBrforeTax, List<CalculateItem> tax , ActualSalaryItem actualSalaryItem){
        Result result;
        CalculateItem condition =new CalculateItem();

        condition.setEmployeeId(actualSalaryItem.getEmployeeId());
        condition.setStartDate(actualSalaryItem.getDate());
        condition.setEndDate(actualSalaryItem.getDate());
        condition.setItemId(28);

        tax = calculateItemService.selectByCondition(condition);
        Double taxValue =0.0;

        if(tax.size()>1){
            result = Result.error();
            result =result.data("Redundant tax!",null);
            return result;
        }else{
            //TODO calcualte tax  value
            taxValue=salaryBrforeTax*0.05;

            condition.setValue(taxValue);
            condition.setDate(actualSalaryItem.getDate());
            if(tax.size()==0){
                calculateItemService.save(condition);
            }else{
                condition.setId(tax.get(0).getId());
                calculateItemService.updateById(condition);
            }
        }

        tax.clear();
        tax.add(condition);

        result = Result.ok();
        result =result.message("settleTax successfully!");
        taxValue*=-1;
        result =result.data("settleTax",taxValue);
        return  result;
    }

    /**
     * 标记被计算项目
     * @param
     * @return
     */
    public Result markSettledItem(ActualSalaryItem actualSalaryItem){
        Result result;
        FixedItem conditionFixedItem=new FixedItem();
        conditionFixedItem.setEmployeeId(actualSalaryItem.getEmployeeId());
        conditionFixedItem.setStartDate(actualSalaryItem.getDate());
        conditionFixedItem.setEndDate(actualSalaryItem.getDate());

        CalculateItem conditionCalculateItem=new CalculateItem();
        conditionCalculateItem.setEmployeeId(actualSalaryItem.getEmployeeId());
        conditionCalculateItem.setStartDate(actualSalaryItem.getDate());
        conditionCalculateItem.setEndDate(actualSalaryItem.getDate());

        List<FixedItem> fixedItems=fixedItemService.selectByCondition(conditionFixedItem);
        List<CalculateItem> calculateItems =calculateItemService.selectByCondition(conditionCalculateItem);

        boolean fixedItemIsOk=true;
        boolean calculateItemIsOk=true;
        for(FixedItem fixedItem:fixedItems){
            fixedItem.setState(1);
            if(!fixedItemService.updateById(fixedItem)){
                fixedItemIsOk=false;
            }
            System.out.println("mark+"+fixedItem);
        }
        for (CalculateItem calculateItem:calculateItems){
            calculateItem.setState(1);
            if(!calculateItemService.updateById(calculateItem)){
                calculateItemIsOk=false;
            }
            System.out.println("mark+"+calculateItem);
        }

        if(!(fixedItemIsOk&&calculateItemIsOk)){
            result = Result.error();
            result =result.message("mark ERROR");
        }else {
            result = Result.ok();
            result =result.message("mark successfully!");
        }

        return  result;
    }

    /**
     * 发放工资，记录改工资事发项目状态位为2，表示已发
     * @param actualSalaryItem
     * @return
     */
    @PostMapping("/paySalary")
    public Result paySalary(@RequestBody ActualSalaryItem actualSalaryItem){
        Result result=null;
        actualSalaryItem.setStartDate(actualSalaryItem.getDate());
        actualSalaryItem.setEndDate(actualSalaryItem.getDate());
        List<ActualSalaryItem> actualSalaryItems =actualSalaryItemService.selectByCondition(actualSalaryItem);
        if(actualSalaryItems.size()>1){
            result = Result.error();
            result =result.message("redundant records");
            return result;
        }else if(actualSalaryItems.size()==0){
            result = Result.error();
            result =result.message("record not exist!");
            return result;
        }else if(actualSalaryItems.size()==1){
            if(actualSalaryItems.get(0).getState()==2){
                result = Result.error();
                result =result.message("alredy paid salary,unable to pay twice!");
                return result;
            }else{
                Result saveSalaryResultResult = saveSalaryResult(actualSalaryItems.get(0));
                if(!saveSalaryResultResult.isSuccess()){
                    result =saveSalaryResultResult;
                    return result;
                }
                actualSalaryItems.get(0).setState(2);
                if (!actualSalaryItemService.updateById(actualSalaryItems.get(0))){
                    result = Result.error();
                    result =result.message("state change error");
                }else {
                    result = Result.ok();
                }
            }
        }

        return result;
    }

    /**
     * 发放工资则收集该条工资的工资条相关款项写入数据库
     * @param actualSalaryItem
     * @return
     */
    public Result saveSalaryResult(ActualSalaryItem actualSalaryItem){
        Result result=null;
        SalaryResult salaryResult =new SalaryResult();

        List<SalaryResult> salaryResults=salaryResultService.querrySalaryForm(null,actualSalaryItem.getDate(),actualSalaryItem.getDate(),actualSalaryItem.getEmployeeId());
        if(salaryResults.size()>0){
            result = Result.error();
            result =result.message("salaryResult exist");
            return result;
        }

        if(actualSalaryItem.getValue()==null){
            result = Result.error();
            result =result.message("null actualSalary");
        }

        salaryResult.setEmployeeId(actualSalaryItem.getEmployeeId());
        salaryResult.setDate(new Date(actualSalaryItem.getDate().getTime()));
        salaryResult.setActualSalary(actualSalaryItem.getValue());

        FixedItem fixedItemCondition =new FixedItem();
        fixedItemCondition.setEmployeeId(actualSalaryItem.getEmployeeId());
        fixedItemCondition.setStartDate(actualSalaryItem.getDate());
        fixedItemCondition.setEndDate(actualSalaryItem.getDate());

        ImportItem importItemCondition =new ImportItem();
        importItemCondition.setEmployeeId(actualSalaryItem.getEmployeeId());
        importItemCondition.setStartDate(actualSalaryItem.getDate());
        importItemCondition.setEndDate(actualSalaryItem.getDate());

        CalculateItem calculateItemCondition =new CalculateItem();
        calculateItemCondition.setEmployeeId(actualSalaryItem.getEmployeeId());
        calculateItemCondition.setStartDate(actualSalaryItem.getDate());
        calculateItemCondition.setEndDate(actualSalaryItem.getDate());

        List<FixedItem> fixedItems =fixedItemService.selectByCondition(fixedItemCondition);
        List<ImportItem> importItems = importItemService.selectByCondition(importItemCondition);
        List<CalculateItem> calculateItems =calculateItemService.selectByCondition(calculateItemCondition);

        for(FixedItem fixedItem :fixedItems){
            if(fixedItem.getItemId()==1){
                salaryResult.setBasicSalary(fixedItem.getValue());
            }else if(fixedItem.getItemId()==2){
                if(salaryResult.getHotSalary()==null){
                    salaryResult.setHotSalary(fixedItem.getValue());
                }else {
                    salaryResult.setHotSalary(salaryResult.getHotSalary()+fixedItem.getValue());
                }
            }else if(fixedItem.getItemId()==3){
                if(salaryResult.getColdSalary()==null){
                    salaryResult.setColdSalary(fixedItem.getValue());
                }else {
                    salaryResult.setColdSalary(salaryResult.getColdSalary()+fixedItem.getValue());
                }
            }else if(fixedItem.getItemId()==14){
                if(salaryResult.getTrafficSalary()==null){
                    salaryResult.setTrafficSalary(fixedItem.getValue());
                }else {
                    salaryResult.setTrafficSalary(salaryResult.getTrafficSalary()+fixedItem.getValue());
                }
            }else if(fixedItem.getItemId()==15){
                if(salaryResult.getHouseSalary()==null){
                    salaryResult.setHouseSalary(fixedItem.getValue());
                }else {
                    salaryResult.setHouseSalary(salaryResult.getHouseSalary()+fixedItem.getValue());
                }
            }
        }

        for(ImportItem importItem:importItems){
            if(importItem.getItemId()==5){
                if(salaryResult.getLateCount()==null){
                    salaryResult.setLateCount(importItem.getValue());
                }else {
                    salaryResult.setLateCount(salaryResult.getLateCount()+importItem.getValue());
                }
            }else if(importItem.getItemId()==6){
                if(salaryResult.getSickLeaveCount()==null){
                    salaryResult.setSickLeaveCount(importItem.getValue());
                }else {
                    salaryResult.setSickLeaveCount(salaryResult.getSickLeaveCount()+importItem.getValue());
                }
            }else if(importItem.getItemId()==11){
                if(salaryResult.getEventLeaveCount()==null){
                    salaryResult.setEventLeaveCount(importItem.getValue());
                }else {
                    salaryResult.setEventLeaveCount(salaryResult.getEventLeaveCount()+importItem.getValue());
                }
            }else if(importItem.getItemId()==16){
                if(salaryResult.getExtraWorkCount()==null){
                    salaryResult.setExtraWorkCount(importItem.getValue());
                }else {
                    salaryResult.setExtraWorkCount(salaryResult.getExtraWorkCount()+importItem.getValue());
                }
            }
        }

        for(CalculateItem calculateItem:calculateItems){
            if(calculateItem.getItemId()==7){
                if(salaryResult.getLateFine()==null){
                    salaryResult.setLateFine(calculateItem.getValue());
                }else {
                    salaryResult.setLateFine(salaryResult.getLateFine()+calculateItem.getValue());
                }
            }else if(calculateItem.getItemId()==9){
                if(salaryResult.getSickLeaveFine()==null){
                    salaryResult.setSickLeaveFine(calculateItem.getValue());
                }else {
                    salaryResult.setSickLeaveFine(salaryResult.getSickLeaveFine()+calculateItem.getValue());
                }
            }else if(calculateItem.getItemId()==12){
                if(salaryResult.getEventLeaveFine()==null){
                    salaryResult.setEventLeaveFine(calculateItem.getValue());
                }else {
                    salaryResult.setEventLeaveFine(salaryResult.getEventLeaveFine()+calculateItem.getValue());
                }
            }else if(calculateItem.getItemId()==17){
                if(salaryResult.getExtraWorkSalary()==null){
                    salaryResult.setExtraWorkSalary(calculateItem.getValue());
                }else {
                    salaryResult.setExtraWorkSalary(salaryResult.getExtraWorkSalary()+calculateItem.getValue());
                }
            }else if(calculateItem.getItemId()==18){
                salaryResult.setCompanyPaidOldIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==19){
                salaryResult.setCompanyPaidMedicalIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==20){
                salaryResult.setCompanyPaidUnemploymentIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==21){
                salaryResult.setCompanyPaidInjuryIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==22){
                salaryResult.setCompanyPaidPregnantIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==26){
                salaryResult.setCompamyPaidHousefund(calculateItem.getValue());
            }else if(calculateItem.getItemId()==23){
                salaryResult.setEmployeePaidOldIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==24){
                salaryResult.setEmployeePaidMedicalIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==25){
                salaryResult.setEmployeePaidUnemploymentIsurance(calculateItem.getValue());
            }else if(calculateItem.getItemId()==27){
                salaryResult.setEmployeePaidHousefund(calculateItem.getValue());
            }else if(calculateItem.getItemId()==28){
                salaryResult.setTax(calculateItem.getValue());
            }
        }
        if(!salaryResultService.save(salaryResult)){
            result = Result.error();
            result =result.message("salaryResult save error");
            return result;
        }

        result = Result.ok();
        result =result.message("salaryResult saved successfully!");

        return result;
    }

}
