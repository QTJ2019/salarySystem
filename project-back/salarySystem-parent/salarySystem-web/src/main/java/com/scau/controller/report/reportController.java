package com.scau.controller.report;

import com.scau.Result.Result;
import com.scau.entity.SalaryResult;
import com.scau.service.SalaryResultService;
import com.scau.service.impl.SalaryResultServiceImpl;
import com.scau.util.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qtj
 * @Date: 2020/9/16 11:42
 * @Version
 */
@RestController
@RequestMapping("/api/report")
public class reportController {
    @Autowired
    SalaryResultService salaryResultService;
    @RequestMapping("/querysalaryform")
    private Result querySalaryForm(String deptName, String start, String end, Integer employeeId){
        Result result = Result.ok();
        Map<String,Object> data;
        Date startDate=null;
        Date endDate=null;

        try {
            if (start != null){
                startDate = CalendarUtil.getFirstDateOfMonth(start);
            }
           if (end != null){
               endDate = CalendarUtil.getLastDateOfMonth(end);
           }

            List<SalaryResult> resultList = salaryResultService.querrySalaryForm(deptName,
                    startDate,
                    endDate,
                    employeeId);
            data = new HashMap<>();
            data.put("salaryForm",resultList);
            result.data(data);
            return result;
        }catch (ParseException e){
            result = result.error();
            result = result.message("日期转换错误");
        }
        catch (Exception e){
             result = Result.error();
             result = result.message("数据库查询失败");

        }
     return result;
    }

}
