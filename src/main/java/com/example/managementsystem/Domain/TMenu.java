package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class TMenu {
    @TableId(value = "menu_id")
    int menuId;
    String menuName;
    String menuHrefUrl;
    String menuVueUrl;
    int MenuParentId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date menuCreateTime;
}
