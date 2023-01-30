package com.dango.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.reggie.common.CustomException;
import com.dango.reggie.entity.Dish;
import com.dango.reggie.entity.DishFlavor;
import com.dango.reggie.entity.Setmeal;
import com.dango.reggie.mapper.DishFlavorMapper;
import com.dango.reggie.service.DishFlavorService;
import com.dango.reggie.service.DishService;
import com.dango.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

}
