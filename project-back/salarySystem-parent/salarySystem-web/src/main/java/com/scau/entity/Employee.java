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
 * @since 2020-09-16
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

    private Date birth;

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

    @TableField("workDate")
    private Date workDate;

    private Integer form;

    private Integer tag;

    private String idcard;

    private String politics;

    private String source;

    private String folk;

    private String nation;

    private String phone;

    private String email;

    private String height;

    private String blood;

    private String status;

    private String homeplace;

    private String seat;

    @TableField("eduBack")
    private String eduBack;

    @TableField("eduDegree")
    private String eduDegree;

    @TableField("graSchool")
    private String graSchool;

    private String speciality;

    @TableField("graDate")
    private Date graDate;


}
