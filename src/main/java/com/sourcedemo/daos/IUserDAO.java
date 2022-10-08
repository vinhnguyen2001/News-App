package com.sourcedemo.daos;

import com.sourcedemo.models.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {

  UserModel findByUserId(Long userId);

  UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
