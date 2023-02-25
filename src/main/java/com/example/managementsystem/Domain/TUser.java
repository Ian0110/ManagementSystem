package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
@Data
public class TUser {
    @TableId(value = "user_id")
    int userId;
    String userAccount;
    String userPwd;
    String userSalt;
    int userSaltIndex;
    int userPwdType;
    int userStatus;
    int userError;
    String userEmpNo;
    int userRoleId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date userCreateTime;
}
