package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class TRole {
    @TableId(value = "role_id")
    int roleId;
    String roleName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date roleCreateTime;
}
