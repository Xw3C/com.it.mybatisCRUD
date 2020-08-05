package com.it.dao;

import com.it.domain.User;

import java.util.List;

//持久层接口
public interface IUserDao {
    //查询所有用户
    List<User> findAll();

    //保存用户
    void saveUser(User user);

    //更新操作
    void updateUser(User user);

    //删除操作 根据ID删除
    void deleteUser(Integer userId);
}
