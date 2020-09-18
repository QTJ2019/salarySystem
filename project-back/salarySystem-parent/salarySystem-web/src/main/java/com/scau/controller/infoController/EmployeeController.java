package com.scau.controller.infoController;


import com.scau.Result.Result;
import com.scau.entity.Department;
import com.scau.entity.Employee;
import com.scau.entity.vo.EmployeeVO;
import com.scau.service.EmployeeService;
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

