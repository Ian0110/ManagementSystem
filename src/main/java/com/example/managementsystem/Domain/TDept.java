package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TDept {
    @TableId(value = "dept_id")
    int deptId;
    String deptNo;
    String deptName;
    int deptPid;
    String deptUser;
    String deptAddress;
}
