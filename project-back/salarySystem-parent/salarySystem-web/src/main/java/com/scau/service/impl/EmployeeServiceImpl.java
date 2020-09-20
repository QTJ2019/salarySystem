package com.scau.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.scau.Result.Result;
import com.scau.entity.Department;
import com.scau.entity.Employee;
import com.scau.entity.Station;
import com.scau.entity.vo.EmployeeVO;
import com.scau.mapper.EmployeeMapper;
import com.scau.service.DepartmentService;
import com.scau.service.EmployeeService;
import com.scau.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 员工 服务实现类
 * </p>
 *
 * @author wyn
 * @since 2020-09-18
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private StationService stationService;

    @Override
    public boolean saveEmployee(EmployeeVO employeeVo) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeVo,employee);
        employee.setEmployDate(new Date());
        QueryWrapper<Station> wrapper=new QueryWrapper<>();
        wrapper.eq("name",employeeVo.getStationName());
        Station station=stationService.getOne(wrapper);
        if(station==null) return false;
        employee.setStationId(station.getId());
        QueryWrapper<Department> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",employeeVo.getDepartmentName());
        Department department=departmentService.getOne(queryWrapper);
        if(department==null) return false;
        employee.setDepartmentId(department.getId());
        boolean flag=this.save(employee);
        return flag;
    }

    @Override
    public void updateEmployee(EmployeeVO employeeVo) {
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",employeeVo.getName());
        Employee employee=this.getOne(queryWrapper);
        BeanUtils.copyProperties(employeeVo,employee);
        boolean flag = this.updateById(employee);
        if(!flag){
            log.error("更新员工信息失败");
        }
    }


}
