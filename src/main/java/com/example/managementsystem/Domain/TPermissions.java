package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class TPermissions {
    @TableId(value = "per_id")
    int perId;
    int perRoleId;
    int perMenuId;
    int perOwn;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date perCreateTime;
}
