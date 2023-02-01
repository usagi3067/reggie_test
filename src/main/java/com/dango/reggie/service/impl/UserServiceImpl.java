package com.dango.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.reggie.entity.Employee;
import com.dango.reggie.entity.User;
import com.dango.reggie.mapper.EmployeeMapper;
import com.dango.reggie.mapper.UserMapper;
import com.dango.reggie.service.EmployeeService;
import com.dango.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
