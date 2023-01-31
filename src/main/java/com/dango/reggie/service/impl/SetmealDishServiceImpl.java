package com.dango.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.reggie.entity.SetmealDish;
import com.dango.reggie.mapper.SetmeaDishMapper;
import com.dango.reggie.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmeaDishMapper, SetmealDish> implements SetmealDishService {
}
