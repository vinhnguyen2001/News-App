package com.sourcedemo.daos.impl;

import com.sourcedemo.daos.IUserDAO;
import com.sourcedemo.mapper.UserMapper;
import com.sourcedemo.models.AbstractModel;
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
    public Long insert(UserModel newUser) {
        StringBuilder  str_query = new StringBuilder("INSERT INTO user(username, password,");
        str_query.append("fullname,roleid,status)");
        str_query.append("VALUES(?,?,?,?,?)");

        return insert(str_query.toString(),newUser.getUserName(),newUser.getPassword(),
                newUser.getFullName(),newUser.getRoleId(),newUser.getStatus());

    }

    @Override
    public void update(UserModel updatingUser) {

        StringBuilder str_query = new StringBuilder("UPDATE user SET username = ?, password = ?,");
        str_query.append("fullname = ?, roleid = ?, status = ?  WHERE id = ?");

        update(str_query.toString(),updatingUser.getUserName(), updatingUser.getPassword()
                ,updatingUser.getFullName(), updatingUser.getRoleId(),
                updatingUser.getStatus(), updatingUser.getId());
    }

    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
        sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
        sql.append(" WHERE username = ? AND password = ? AND status = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
        return users.isEmpty() ? null : users.get(0);
    }

}
