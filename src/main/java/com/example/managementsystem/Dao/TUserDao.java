package com.example.managementsystem.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.managementsystem.Domain.TUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserDao extends BaseMapper<TUser> {
}
