package com.scau.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.scau.entity.Employee;
import com.scau.mapper.EmployeeMapper;
import com.scau.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工 服务实现类
 * </p>
 *
 * @author wyn
 * @since 2020-09-18
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
