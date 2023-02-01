package com.dango.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dango.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
