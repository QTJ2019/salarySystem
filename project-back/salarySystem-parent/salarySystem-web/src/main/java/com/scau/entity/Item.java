package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data

@TableName("item")
public class Item implements Serializable {
    @TableId(type = IdType.AUTO)
    //工资项目id
    private Integer id;
    //项目名
    private String name;
    //项目类型(固定；导入；计算；实发)
    private String type;
    //生效位(1增加项，-1减少项，0无影响,用于区分工资项目计算时候的正负，如：迟到罚款计算时*-1成为减少项)
    private Integer effective;
    //项目描述
    private String description;

}
