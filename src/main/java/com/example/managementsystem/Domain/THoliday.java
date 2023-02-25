package com.example.managementsystem.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class THoliday {
    @TableId(value = "holiday_id",type = IdType.AUTO)
    int holidayId;
    String holidayNo;
    String holidayUserNo;
    int holidayTypeId;
    String holidayBz;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date HolidayStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date HolidayEndTime;
    String HolidayStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date HolidayCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date HolidayUpdateTime;
}
