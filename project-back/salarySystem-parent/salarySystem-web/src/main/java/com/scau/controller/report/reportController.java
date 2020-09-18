package com.scau.controller.report;

import com.alibaba.excel.EasyExcel;
import com.scau.Result.Result;
import com.scau.entity.CompanyStatistic;
import com.scau.entity.DeptStatistic;
import com.scau.entity.SalaryResult;
import com.scau.service.SalaryResultService;
import com.scau.service.impl.SalaryResultServiceImpl;
import com.scau.util.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        if (startDate==null && endDate!=null)
            return Result.error().message("请选择开始时间");
        if (startDate!=null && endDate == null)
            return Result.error().message("请选择结束时间");
        try {
            if (start!= null && end != null){
                startDate = CalendarUtil.getFirstDateOfMonth(start);
                endDate = CalendarUtil.getLastDateOfMonth(end);
            }

            List<SalaryResult> resultList = salaryResultService.querrySalaryForm(deptName,
                    startDate,
                    endDate,
                    employeeId);
            data = new HashMap<>();
            data.put("salaryForm",resultList);
            result.data(data);
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

    @RequestMapping("/exportsalaryform")
    private Result exportSalaryForm(String deptName, String start, String end, Integer employeeId, HttpServletResponse response){
        Result result = Result.error();
        List<SalaryResult> resultList = null;
        Date startDate=null;
        Date endDate=null;
        if (startDate==null && endDate!=null)
            return Result.error().message("请选择开始时间");
        if (startDate!=null && endDate == null)
            return Result.error().message("请选择结束时间");
        try {
            if (start!= null && end != null){
                startDate = CalendarUtil.getFirstDateOfMonth(start);
                endDate = CalendarUtil.getLastDateOfMonth(end);
            }
            resultList = salaryResultService.querrySalaryForm(deptName,
                                                             startDate,
                                                             endDate,
                                                             employeeId);
        }catch (ParseException e){
            return result.message("日期转换错误");
        }
        catch (Exception e){
            return result.message("数据库查询失败");
        }

        String fileName = null;
        try {
            fileName = URLEncoder.encode("工资报表","utf-8");
        } catch (UnsupportedEncodingException e) {
            result = Result.error();
            return result.message("报表名称编码错误");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setContentType("utf-8");
        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(),SalaryResult.class).sheet("sheet1").doWrite(resultList);
        } catch (IOException e) {
            return result.message("输出流错误");
        }

        return Result.ok();
    }

    @RequestMapping("/getdeptstatistic")
    private Result getDeptStatistic(String deptName,String start,String end){
        Result result = Result.ok();
        List<DeptStatistic> resultList = null;
        Map<String,Object> data = null;
        Date startDate = null;
        Date endDate = null;
        if (start==null && end!=null)
            return Result.error().message("请选择开始时间");
        if (start!=null && end == null)
            return Result.error().message("请选择结束时间");

        try {
            if (start!= null && end != null){
                startDate = CalendarUtil.getFirstDateOfMonth(start);
                endDate = CalendarUtil.getLastDateOfMonth(end);
            }
        }catch (ParseException e){
            return Result.error().message("日期转换出错");
        }

        try {
           resultList =  salaryResultService.queryDeptStatistic(deptName,startDate,endDate);
           data = new HashMap<>();
           data.put("deptStatistics",resultList);
           result.data(data);
        } catch (Exception e) {
            return Result.error().message("查询数据失败");
        }
        return result;
    }

    @RequestMapping("/getcompanystatistic")
    private Result getCompanyStatistic(String start, String end){
        Result result = Result.ok();
        CompanyStatistic companyStatistic = null;
        Date startDate = null;
        Date endDate = null;
        Map<String,Object> data = null;
        if (start==null && end!=null)
            return Result.error().message("请选择开始时间");
        if (start!=null && end == null)
            return Result.error().message("请选择结束时间");
        try {
            if (start!= null && end != null){
                startDate = CalendarUtil.getFirstDateOfMonth(start);
                endDate = CalendarUtil.getLastDateOfMonth(end);
            }
        }catch (ParseException e){
            return Result.error().message("日期转换出错");
        }
        try {
            companyStatistic =  salaryResultService.queryCompanyStatistic(startDate,endDate);
            data = new HashMap<>();
            data.put("companyStatistic",companyStatistic);
            result.data(data);
        } catch (Exception e) {
            return Result.error().message("查询数据失败");
        }
        return result;
    }

}
