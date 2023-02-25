package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TConfig {
    @TableId(value = "config_id")
    int configId;
    String configName;
    String configType;
    String configRemark;
}
