package com.dango.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.reggie.common.CustomException;
import com.dango.reggie.dto.SetmealDto;
import com.dango.reggie.entity.Setmeal;
import com.dango.reggie.entity.SetmealDish;
import com.dango.reggie.mapper.SetmealMapper;
import com.dango.reggie.service.SetmealDishService;
import com.dango.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 新增套餐， 同时需要保存套餐和菜品的关联关系
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息， 操作setmeal,执行insert
        this.save(setmealDto);
        //保存套餐和菜品的关联信息， 操作setmeal_dish,执行insert
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     *  删除套餐， 同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //select count(*) from xxx where id in ids and status = 1
        //查询套餐状态， 确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        //如果不能删除， 抛出一个业务异常
        int count = this.count(queryWrapper);
        if (count > 0) {
            //如果不能删除， 抛出一个业务异常
            throw new CustomException("套餐正在售卖中， 不能删除");
        }

        //如果可以删除， 先删除套餐表中的数据
        this.removeByIds(ids);

        //删除关系表中的数据
        //delete from setmeal_dish where setmeal_id in ids
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);

        setmealDishService.remove(lambdaQueryWrapper);
    }
}
