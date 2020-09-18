package com.scau.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data

public class FixedItemVO implements Serializable {

    private Integer id;

    private Integer departmentId;

    private String departmentName;

    private Integer stationId;

    private String stationName;

    private Integer employeeId;

    private String employeeName;

    private Integer itemId;

    private String itemName;

    private Double value;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date date;
}
