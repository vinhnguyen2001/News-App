package com.sourcedemo.services.impl;

import com.sourcedemo.daos.IUserDAO;
import com.sourcedemo.models.UserModel;
import com.sourcedemo.services.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {


    @Inject
    private IUserDAO userDAO;
//    private UserDAO user = new UserDAO();
    @Override
    public UserModel findByUserId(Long userId) {
       return  userDAO.findByUserId(userId);
    }



    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        return userDAO.findByUserNameAndPasswordAndStatus(userName,password, status);
    }
}
