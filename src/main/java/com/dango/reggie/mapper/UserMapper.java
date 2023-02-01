package com.dango.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dango.reggie.entity.Category;
import com.dango.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
