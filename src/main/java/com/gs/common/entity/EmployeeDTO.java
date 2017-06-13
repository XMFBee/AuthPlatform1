package com.gs.common.entity;

import com.gs.bean.Salary;

import java.io.Serializable;
import java.util.Date;

@ModelTitle(name="工资列表")
public class EmployeeDTO extends Salary implements Serializable {

    private static final long serialVersionUID = -3434719712955859295L;

    private Long id;
    @ModelProp(name = "工资发放编号", colIndex = 0, nullable = false)
    private String salaryId;

    @ModelProp(name = "用户编号", colIndex = 1, nullable = false)
    private String userId;

    @ModelProp(name = "奖金", colIndex = 2, nullable = false)
    private Double prizeSalary;

    @ModelProp(name = "罚款", colIndex = 3, nullable = false)
    private Double minusSalay;

    @ModelProp(name = "总工资", colIndex = 4, nullable = false)
    private Double totalSalary;

    @ModelProp(name = "工资发放描述", colIndex = 5, nullable = false)
    private String salaryDes;

    @ModelProp(name = "工资发放时间", colIndex = 6, nullable = false)
    private Date salaryTime;
    @ModelProp(name = "工资发放创建时间", colIndex = 7, nullable = false)
    private Date salaryCreatedTime;
}