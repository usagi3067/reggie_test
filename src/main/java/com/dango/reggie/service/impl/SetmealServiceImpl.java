package com.dango.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.reggie.entity.Setmeal;
import com.dango.reggie.mapper.SetmealMapper;
import com.dango.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
