package com.easy.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.demo.bean.pojo.User;
import com.easy.demo.dao.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author muchi
 */
@Service
@AllArgsConstructor
public class UserService extends ServiceImpl<UserMapper, User> {

    private final UserMapper userMapper;

    public User login(User user) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, user.getName()));
    }


}
