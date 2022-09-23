package com.sourcedemo.services;

import com.sourcedemo.models.UserModel;

import java.util.List;

public interface IUserService {

    UserModel findByUserId(Long userId);

    public UserModel save(UserModel newUser);

    public  UserModel update(UserModel updatingUser);
}
