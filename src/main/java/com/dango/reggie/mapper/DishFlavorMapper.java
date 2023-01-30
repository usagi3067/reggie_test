package com.dango.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dango.reggie.entity.Category;
import com.dango.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
