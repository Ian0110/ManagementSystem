package com.example.managementsystem.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LeaveForm {
    public LeaveForm() {

    }

    public LeaveForm(String holidayNo,String holidayUserNo,String holidayType,String holidayBz,Date holidayEndTime,Date holidayStartTime,String holidayStatus){
        setHolidayUserNo(holidayUserNo);
        setHolidayStatus(holidayStatus);
        setHolidayType(holidayType);
        setHolidayNo(holidayNo);
        setHolidayStartTime(holidayStartTime);
        setHolidayEndTime(holidayEndTime);
        setHolidayBz(holidayBz);
    }
    String holidayNo;
    String holidayUserNo;
    String holidayType;
    String holidayBz;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date HolidayStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date HolidayEndTime;
    String HolidayStatus;
}
