package com.dango.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dango.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
