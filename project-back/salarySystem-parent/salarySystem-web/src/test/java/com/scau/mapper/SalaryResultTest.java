package com.scau.mapper;

import com.scau.entity.SalaryResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: qtj
 * @Date: 2020/9/16 14:22
 * @Version
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaryResultTest {
    @Autowired
    private SalaryResultMapper salaryResultMapper;

    @Test
    public void test(){
        List<SalaryResult> list = salaryResultMapper.selectList(null);
        Assert.assertEquals(1,list.size());
    }
}
