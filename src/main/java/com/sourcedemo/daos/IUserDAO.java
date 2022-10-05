package com.sourcedemo.daos;

import com.sourcedemo.models.UserModel;

import java.util.List;

public interface IUserDAO extends GenericDAO<UserModel> {

  UserModel findByUserId(Long userId);

  UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
