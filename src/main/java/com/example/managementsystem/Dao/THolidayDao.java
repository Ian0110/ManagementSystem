package com.example.managementsystem.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.managementsystem.Domain.THoliday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface THolidayDao extends BaseMapper<THoliday> {
    String getHolidayType(@Param("id")int id);
}
