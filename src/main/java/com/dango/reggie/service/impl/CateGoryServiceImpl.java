package com.dango.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.reggie.entity.Category;
import com.dango.reggie.mapper.CategoryMapper;
import com.dango.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CateGoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
