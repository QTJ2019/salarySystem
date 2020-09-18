package com.scau.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.Department;
import com.scau.mapper.DepartmentMapper;
import com.scau.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author wyn
 * @since 2020-09-16
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
