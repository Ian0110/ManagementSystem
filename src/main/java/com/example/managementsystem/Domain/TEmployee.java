package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class TEmployee {
    @TableId(value = "emp_id")
    int empId;
    String empNo;
    String empName;
    int empDeptId;
    String empSex;
    String empEducation;
    String empEmail;
    String empPhone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date empEntryTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date empCreateTime;
}
