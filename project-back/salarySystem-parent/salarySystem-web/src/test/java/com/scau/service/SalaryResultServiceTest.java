package com.scau.service;

import com.scau.entity.SalaryResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qtj
 * @Date: 2020/9/16 15:55
 * @Version
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaryResultServiceTest {
    @Autowired
    private SalaryResultService salaryResultService;

    @Test
    public void testQuerrySalaryForm(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",1);
        List<SalaryResult> list = salaryResultService.querrySalaryForm(map);
        Assert.assertEquals(1,list.size());
        System.out.println(list.get(0).getEmployeeId());
    }
}
