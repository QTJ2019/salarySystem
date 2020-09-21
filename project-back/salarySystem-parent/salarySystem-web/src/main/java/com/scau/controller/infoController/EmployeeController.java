package com.scau.controller.infoController;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.Result.Result;
import com.scau.entity.Department;
import com.scau.entity.Employee;
import com.scau.entity.vo.EmployeeQuery;
import com.scau.entity.vo.EmployeeVO;
import com.scau.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工 前端控制器
 * </p>
 *
 * @author wyn
 * @since 2020-09-18
 */
@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 添加员工信息信息
     * @param employeeVo
     * @return
     */
    @PostMapping("addEmployee")
    public Result addEmployee(@RequestBody EmployeeVO employeeVo){
        boolean flag=employeeService.saveEmployee(employeeVo);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @GetMapping("removeEmployee/{id}")
    public Result removeEmployee(@PathVariable("id") Integer id){
        boolean flag=employeeService.removeById(id);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }


    /**
     * 查询所有员工的信息
     * @return
     */
    @GetMapping("getAllEmployee")
    public Result getAllEmployee(){
        List<Employee> list=employeeService.list(null);
        return Result.ok().data("list",list);
    }


    /**
     * 根据条件查询员工信息
     * @param employeeQuery
     * @return
     */
    @PostMapping("getEmployeeByCondition")
    public Result getEmployeeByCondition(@RequestBody(required = false) EmployeeQuery employeeQuery){
        String name=employeeQuery.getName();
        String deptName=employeeQuery.getDeptName();
        String stationName=employeeQuery.getStationName();
        QueryWrapper<Employee> wrapper=new QueryWrapper<>();
        if(StringUtils.isEmpty(name)){
            wrapper.eq("name",name);
        }
        if(StringUtils.isEmpty(deptName)){
            wrapper.eq("departmentName;",deptName);
        }
        if(StringUtils.isEmpty(stationName)){
            wrapper.eq("stationName",stationName);
        }
        Employee employee = employeeService.getOne(wrapper);
        if(employee==null){
            log.error("该员工不存在");
        }
        return Result.ok().data("employee",employee);
    }

    /**
     * 根据员工id获取员工信息
     * @param id
     * @return
     */
    @GetMapping("getEmployeeById/{id}")
    public Result getEmployeeById(@PathVariable("id") Integer id){
        Employee employee=employeeService.getById(id);
        if(employee==null){
            log.error("该员工不存在");
            return Result.error();
        }else{
            return Result.ok().data("employee",employee);
        }
    }

    /**
     * 更新员工信息,更新员工住址信息
     * @param employeeVo
     * @return
     */
    @PostMapping("updateEmployee")
    public Result updateEmployee(@RequestBody EmployeeVO employeeVo){
        employeeService.updateEmployee(employeeVo);
        return Result.ok();
    }



}

