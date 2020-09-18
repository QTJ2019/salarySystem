package com.scau.controller.infoController;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.Result.Result;
import com.scau.entity.Department;
import com.scau.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author wyn
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加部门
     * @param department
     * @return
     */
    @PostMapping("addDepartment")
    public Result addDepartment(@RequestBody Department department){
        department.setDate(new Date());
        boolean save=departmentService.save(department);
        if(save){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    /**
     * 删除一个部门
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    public Result removeDepartment(@PathVariable("id") Integer id){
        boolean flag=departmentService.removeById(id);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    /**
     * 查询所有部门的信息
     * @return
     */
    @GetMapping("getAllDepartment")
    public Result getAllDepartment(){
        List<Department> list=departmentService.list(null);
        return Result.ok().data("list",list);
    }

    /**
     * 分页查询所有部门信息
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("pageDepartment/{current}/{limit}")
    public Result pageDepartment(@PathVariable("current") long current,@PathVariable("limit") long limit){
        Page<Department> page=new Page<>(current,limit);
        departmentService.page(page,null);
        long total=page.getTotal();
        List<Department> records=page.getRecords();
        Map<String,Object> maps=new HashMap<>();
        maps.put("total",total);
        maps.put("items",records);
        return Result.ok().data(maps);
    }

    /**
     * 修改部门信息
     * @param department
     * @return
     */
    @PostMapping("updateDepartment")
    public Result updateDepartment(@RequestBody Department department){
        boolean flag=departmentService.updateById(department);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

}

