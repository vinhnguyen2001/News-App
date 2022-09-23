package com.sourcedemo.mapper;


import com.sourcedemo.models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {


    @Override
    public UserModel mapRow(ResultSet rs) {

        try {
            UserModel user = new UserModel();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("fullname"));
            user.setRoleId(rs.getLong("roleid"));
            user.setStatus(rs.getInt("status"));
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
