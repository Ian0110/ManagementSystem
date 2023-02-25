package com.example.managementsystem.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.managementsystem.Domain.TEmployee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TEmployeeDao extends BaseMapper<TEmployee> {
}
