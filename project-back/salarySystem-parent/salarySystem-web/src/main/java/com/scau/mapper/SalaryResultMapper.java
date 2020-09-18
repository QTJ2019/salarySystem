package com.scau.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.entity.DeptStatistic;
import com.scau.entity.SalaryResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: qtj
 * @Date: 2020/9/16 13:55
 * @Version
 */
@Repository
@Component
public interface SalaryResultMapper extends BaseMapper<SalaryResult> {

    List<SalaryResult> querySalaryForm(@Param("deptName") String deptName,
                                       @Param("startMonth") Date startMonth,
                                       @Param("endMonth") Date endMonth,
                                        @Param("employeeId") Integer employeeId);

    List<DeptStatistic> queryDeptStatistic(@Param("deptName")String deptName,
                                           @Param("startMonth") Date startMonth,
                                           @Param("endMonth") Date endMonth);


}
