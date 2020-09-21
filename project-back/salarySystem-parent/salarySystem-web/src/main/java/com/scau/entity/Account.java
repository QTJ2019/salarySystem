package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Account {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String account;

    private String password;

}
