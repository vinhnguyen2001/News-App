package com.sourcedemo.daos.impl;

import com.sourcedemo.daos.IUserDAO;
import com.sourcedemo.mapper.UserMapper;
import com.sourcedemo.models.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{


    @Override
    public UserModel findByUserId(Long userId) {

        String str_query = "SELECT * FROM User WHERE id = ?";
        List<UserModel> users = query(str_query, new UserMapper(), userId);
        return users.isEmpty()? null :  users.get(0);

    }

    
    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
        sql.append(" INNER JOIN newservlet.role AS r ON r.id = u.roleid");
        sql.append(" WHERE username = ? AND password = ? AND status = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
        return users.isEmpty() ? null : users.get(0);
    }


}
