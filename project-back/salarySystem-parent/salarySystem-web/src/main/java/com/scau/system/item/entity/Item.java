package com.scau.system.item.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data

@TableName("item")
public class Item implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String type;

    private Integer effective;

    private String description;

}
