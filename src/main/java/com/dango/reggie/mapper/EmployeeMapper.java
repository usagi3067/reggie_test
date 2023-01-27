package com.dango.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dango.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
