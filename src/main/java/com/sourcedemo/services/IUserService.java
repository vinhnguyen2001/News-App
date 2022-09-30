package com.sourcedemo.services;

import com.sourcedemo.models.UserModel;

public interface IUserService {

    UserModel findByUserId(Long userId);

    public UserModel save(UserModel newUser);

    public  UserModel update(UserModel updatingUser);

}
