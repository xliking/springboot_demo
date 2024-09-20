package com.easy.demo.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.demo.bean.pojo.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 木池
 */
@Mapper
@DS("mysql_yun")
public interface UserMapper extends BaseMapper<User> {


}
