package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;


@Data

@TableName("import_item")
public class ImportItem {
    @TableId(type = IdType.AUTO)
    //固定项目id
    private Integer id;

    //员工id
    private Integer employee_id;

    //项目id
    private Integer item_id;

    //项目值
    private Integer value;

    //日期
    private Date date;

}
