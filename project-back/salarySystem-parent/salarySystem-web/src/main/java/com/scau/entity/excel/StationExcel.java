package com.scau.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StationExcel {

    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private String deptName;



}
