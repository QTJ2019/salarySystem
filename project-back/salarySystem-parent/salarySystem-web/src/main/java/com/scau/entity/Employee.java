package com.scau.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author wyn
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer sex;

    @TableField("departmentId")
    private Integer departmentId;

    @TableField("departmentName")
    private String departmentName;

    @TableField("stationId")
    private Integer stationId;

    @TableField("stationName")
    private String stationName;

    @TableField("employDate")
    private Date employDate;

    private String idcard;

    private String folk;

    private String phone;

    private String email;

    private String status;

    private String seat;

    @TableField("eduBack")
    private String eduBack;

    @TableField("graSchool")
    private String graSchool;

    private String speciality;


}
