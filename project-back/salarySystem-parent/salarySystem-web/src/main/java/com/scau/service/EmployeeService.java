package com.scau.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.Employee;
import com.scau.entity.vo.EmployeeVO;


/**
 * <p>
 * 员工 服务类
 * </p>
 *
 * @author wyn
 * @since 2020-09-18
 */
public interface EmployeeService extends IService<Employee> {

    boolean saveEmployee(EmployeeVO employeeVo);

    void updateEmployee(EmployeeVO employeeVo);
}
