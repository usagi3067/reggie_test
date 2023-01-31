package com.dango.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dango.reggie.dto.SetmealDto;
import com.dango.reggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐， 同时需要保存套餐和菜品的关联关系
     */
    void saveWithDish(SetmealDto setmealDto);
}
