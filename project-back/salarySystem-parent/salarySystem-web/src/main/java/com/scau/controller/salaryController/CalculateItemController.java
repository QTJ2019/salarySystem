package com.scau.controller.salaryController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scau.Result.Result;
import com.scau.entity.*;
import com.scau.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/salary/calculateItem")
public class CalculateItemController {
    @Autowired
    private CalculateItemService calculateItemService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private FixedItemService fixedItemService;

    @Autowired
    private ImportItemService importItemService;

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
//            System.out.println(calculateItem);
            result= Result.ok();
            result= result.data("The search result is empty!",null);
        }else{
            result = Result.ok();
            result=result.data("data",calculateItems);
        }
        return result;

    }

    /**
     * 通过员工id，项目id，具体值(非必须)，日期插入一条固定项目记录
     * @param calculateItem
     * @return
     */
    @PostMapping(value = "/addRecord")
    public Result addRecord(@RequestBody CalculateItem calculateItem){
        Result result = null;
        if(calculateItem==null){
            result =result.error();
            result =result.message("null object!");
        }else if(calculateItem.getEmployeeId()==null){
            result =result.error();
            result =result.message("null employeeId!");
        }else if(calculateItem.getEmployeeId()<=0){
            result =result.error();
            result =result.message("illegal employeeId!");
        }else if(!employeeIsExist(calculateItem.getEmployeeId())){
            result =result.error();
            result =result.message("the employeeId Not Exist!");
        }else if(calculateItem.getItemId()==null){
            result =result.error();
            result =result.message("null itemId!");
        }else if(calculateItem.getItemId()<=0){
            result =result.error();
            result =result.message("illegal itemId!");
        }else if(!itemIsExist(calculateItem.getItemId())){
            result =result.error();
            result =result.message("the itemId Not Exist!");
        }else if(calculateItem.getDate()==null){
            result =result.error();
            result =result.message("null date!");
        }else{
            boolean isSaved = calculateItemService.save(calculateItem);
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
    public Result changeRecord(@RequestBody CalculateItem calculateItem){
        Result result;
        boolean isChanged = calculateItemService.updateById(calculateItem);
        if(isChanged){
            result = Result.ok();
        }else{
            result = Result.error();
        }
        return result;
    }

    @PostMapping("/deleteRecord")
    public Result deleteRecord(@RequestBody CalculateItem calculateItem){
        Result result;
        boolean isDeleted =calculateItemService.removeById(calculateItem.getId());
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
     * 生成一个员工某个月的除个人所得税之外的计算项目
     * @param calculateItem
     * @return
     */
    @PostMapping("calculateOneEmployee")
    public Result calculate(@RequestBody CalculateItem calculateItem){
        System.out.println("calculate");
        Result result = null;
        List<Result> resultSet =new ArrayList<Result>();
        FixedItem basciSlary=new FixedItem();

        basciSlary.setEmployeeId(calculateItem.getEmployeeId());
        basciSlary.setItemName("基本工资");
        basciSlary.setStartDate(calculateItem.getDate());
        basciSlary.setEndDate(calculateItem.getDate());

        basciSlary.setState(0);

        List<FixedItem> fixedItems=fixedItemService.selectByCondition(basciSlary);

        if(fixedItems==null || fixedItems.size()==0){
            result= Result.error();
            result=result.message("lack of basicSalary!");
        }else {
            basciSlary =fixedItems.get(0);
            System.out.println(basciSlary);
            List<CalculateItem> calculateItems=new ArrayList<CalculateItem>();
            calculateImportItem(calculateItems,calculateItem.getEmployeeId(),calculateItem.getDate());
            int calculateImportItemNum=calculateItems.size();
            Result calculateImpotItemResult= Result.ok();
            calculateImpotItemResult=calculateImpotItemResult.message(calculateImportItemNum+" importItem(s) to calculate.");
            resultSet.add(calculateImpotItemResult);
            calculateInsuranceAndHousingFund(calculateItems,calculateItem.getEmployeeId(),basciSlary.getValue(),calculateItem.getDate());
            calculateItems.removeAll(Collections.singleton(null));
            int calculateInsuranceAndHousingFundNum=calculateItems.size()-calculateImportItemNum;
            Result calculateInsuranceAndHousingFundResult = Result.ok();
            calculateInsuranceAndHousingFundResult=calculateInsuranceAndHousingFundResult.message(calculateInsuranceAndHousingFundNum+" InsuranceAndHousingFund to calculate.");
            resultSet.add(calculateInsuranceAndHousingFundResult);
            for(CalculateItem each :calculateItems){
                if(each!=null){
                    resultSet.add(addRecord(each));
                    System.out.println(each);
                }
            }
            System.out.println(calculateItems.size());
            markCalculatedImportItem(calculateItem.getEmployeeId(),calculateItem.getDate());
            result= Result.ok();
            result=result.data("results",resultSet);
        }


        return result;
    }


    /**
     * 计算所有未计算的导入项目，生成对应的计算项目
     * @param calculateItems
     * @param employeeId
     * @param date
     * @return
     */
    public void calculateImportItem(List<CalculateItem> calculateItems, Integer employeeId, Date date){
        System.out.println("calculateImportItem");
        ImportItem condition =new ImportItem();
        condition.setEmployeeId(employeeId);
        condition.setDate(date);
        condition.setStartDate(date);
        condition.setEndDate(date);
        condition.setState(0);
        List<ImportItem> importItems =importItemService.selectByCondition(condition);

        for(ImportItem importItem:importItems){
            CalculateItem importCalculateItem =new CalculateItem();
            importCalculateItem.setEmployeeId(employeeId);
            importCalculateItem.setDate(date);
            importCalculateItem.setStartDate(date);
            importCalculateItem.setEndDate(date);
            if(importItem.getItemId()==5){
                importCalculateItem.setItemId(7);
                importCalculateItem.setValue(importItem.getValue()*10.0);
            }else if(importItem.getItemId()==6){
                importCalculateItem.setItemId(9);
                importCalculateItem.setValue(importItem.getValue()*5.0);
            }else if(importItem.getItemId()==11){
                importCalculateItem.setItemId(12);
                importCalculateItem.setValue(importItem.getValue()*10.0);
            }else if(importItem.getItemId()==16){
                importCalculateItem.setItemId(17);
                importCalculateItem.setValue(importItem.getValue()*100.0);
            }
            calculateItems.add(importCalculateItem);
        }

    }

    /**
     * 标记被计算了的导入项目
     * @param employeeId
     * @param date
     */
    public void markCalculatedImportItem(Integer employeeId,Date date){
        System.out.println("markCalculatedImportItem");
        ImportItem condition =new ImportItem();
        condition.setEmployeeId(employeeId);
        condition.setStartDate(date);
        condition.setEndDate(date);
        condition.setState(0);
        List<ImportItem> importItems =importItemService.selectByCondition(condition);

        if(importItems!=null && importItems.size()>0){

            for(ImportItem changedImportItem: importItems){
                if(changedImportItem!=null){
                    if(changedImportItem.getState()!=null){
                        changedImportItem.setState(1);
                    }
                    importItemService.updateById(changedImportItem);
                }
            }
        }
    }

    /**
     * 计算五险一金并生成对应的计算项目
     * @param calculateItems
     * @param employeeId
     * @param basicSalary
     * @param date
     */
    public void calculateInsuranceAndHousingFund(List<CalculateItem> calculateItems, Integer employeeId, Double basicSalary, Date date){
        System.out.println("calculateInsuranceAndHousingFund");
        //公司缴纳养老保险
        calculateItems.add(calculateCompanyPaidOldIsurance(employeeId,basicSalary,date));
        //个人缴纳养老保险
        calculateItems.add(calculateEmployeePaidOldIsurance(employeeId,basicSalary,date));
        //公司缴纳医疗保险
        calculateItems.add(calculateCompanyPaidMedicalIsurance(employeeId,basicSalary,date));
        //个人缴纳医疗保险
        calculateItems.add(calculateEmployeePaidMedicalIsurance(employeeId,basicSalary,date));
        //公司缴纳失业保险
        calculateItems.add(calculateCompanyPaidUnemploymentIsurance(employeeId,basicSalary,date));
        //个人缴纳失业保险
        calculateItems.add(calculateEmployeePaidUnemploymentIsurance(employeeId,basicSalary,date));
        //公司缴纳工伤保险
        calculateItems.add(calculateCompanyPaidInjuryIsurance(employeeId,basicSalary,date));
        //公司缴纳生育保险
        calculateItems.add(calculateCompanyPaidPregnantIsurance(employeeId,basicSalary,date));
        //公司缴纳住房公积金
        calculateItems.add(calculateCompamyPaidHousefund(employeeId,basicSalary,date));
        //个人缴纳住房公积金
        calculateItems.add(calculateEmployeePaidHousefund(employeeId,basicSalary,date));
    }

    /**
     * 检查是否已存在五险一金记录
     * @param employeeId
     * @param itemId
     * @param date
     * @return
     */
    public boolean isExistInsuranceAndHousingFund(Integer employeeId,Integer itemId, Date date){
        System.out.println("isExist"+itemId);
        boolean isExist=true;
        CalculateItem condition =new CalculateItem();
        condition.setEmployeeId(employeeId);
        condition.setStartDate(date);
        condition.setEndDate(date);
        condition.setItemId(itemId);
        List<CalculateItem> calculateItemList =calculateItemService.selectByCondition(condition);
        if(calculateItemList==null ||  calculateItemList.size()==0){
            isExist=false;
        }else {
            isExist=true;
        }
        System.out.println(isExist);
        return isExist;
    }

    /**
     * 计算五险一金：公司缴纳养老保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateCompanyPaidOldIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,18,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(18);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.21);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：个人缴纳养老保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateEmployeePaidOldIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,23,date)){
           return null;
        }else {
            CalculateItem calculateItem = new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(23);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary * 0.08);
            return calculateItem;
        }
    }


    /**
     * 计算五险一金：公司缴纳医疗保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateCompanyPaidMedicalIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,19,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(19);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.09);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：个人缴纳医疗保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateEmployeePaidMedicalIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,24,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(24);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.02);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：公司缴纳失业保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateCompanyPaidUnemploymentIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,20,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(20);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.02);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：个人缴纳失业保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateEmployeePaidUnemploymentIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,25,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(25);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.01);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：单位缴纳工伤保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateCompanyPaidInjuryIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,21,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(21);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.005);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：单位缴纳生育保险
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateCompanyPaidPregnantIsurance(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,22,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(22);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.008);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：单位缴纳住房公积金
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateCompamyPaidHousefund(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,26,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(26);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.08);
            return calculateItem;
        }

    }

    /**
     * 计算五险一金：个人缴纳住房公积金
     * @param employeeId
     * @param basicSalary
     * @param date
     * @return
     */
    public CalculateItem calculateEmployeePaidHousefund(Integer employeeId, Double basicSalary, Date date){
        if(isExistInsuranceAndHousingFund(employeeId,27,date)){
            return null;
        }else {
            CalculateItem calculateItem =new CalculateItem();
            calculateItem.setEmployeeId(employeeId);
            calculateItem.setItemId(27);
            calculateItem.setDate(date);
            calculateItem.setValue(basicSalary*0.08);
            return calculateItem;
        }

    }






}
