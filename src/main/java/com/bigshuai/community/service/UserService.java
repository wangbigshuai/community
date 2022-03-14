package com.bigshuai.community.service;

import com.bigshuai.community.dao.UserMapper;
import com.bigshuai.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//是为了 根据id 找到用户名称 然后拼接在一起 做评论的功能 而不用连接查询
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }
}
