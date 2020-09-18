package com.scau.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
public class Limitation implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private  Double minValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private Double maxValue;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private Date endDate;
}
