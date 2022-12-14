package com.sourcedemo.services;

import com.sourcedemo.models.UserModel;

public interface IUserService {

    UserModel findByUserId(Long userId);

    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

}
