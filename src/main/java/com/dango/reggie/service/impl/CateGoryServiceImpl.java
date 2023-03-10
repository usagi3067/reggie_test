package com.dango.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.reggie.common.CustomException;
import com.dango.reggie.entity.Category;
import com.dango.reggie.entity.Dish;
import com.dango.reggie.entity.Setmeal;
import com.dango.reggie.mapper.CategoryMapper;
import com.dango.reggie.service.CategoryService;
import com.dango.reggie.service.DishService;
import com.dango.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CateGoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id 删除分类， 删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品， 如果已经关联， 抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        if (count1 > 0) {
            //已经关联了菜品， 抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品， 不能删除");
        }
        //查询当前分类是否关联了套餐， 如果已经关联， 抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setMealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setMealLambdaQueryWrapper);

        if (count2 > 0) {
            //已经关联了套餐， 抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐， 不能删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
