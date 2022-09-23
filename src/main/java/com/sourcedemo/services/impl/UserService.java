package com.sourcedemo.services.impl;

import com.sourcedemo.daos.IUserDAO;
import com.sourcedemo.daos.impl.UserDAO;
import com.sourcedemo.models.UserModel;
import com.sourcedemo.services.IUserService;
import javax.inject.Inject;
import java.util.List;

public class UserService implements IUserService {


    @Inject
    private IUserDAO userDAO;
    private UserDAO user = new UserDAO();
    @Override
    public UserModel findByUserId(Long userId) {
       return  userDAO.findByUserId(userId);
    }

    @Override
    public UserModel save(UserModel newUser) {

        Long newUserId = userDAO.insert(newUser);

        return userDAO.findByUserId(newUserId);
    }

    @Override
    public  UserModel update(UserModel updatingUser) {

        Long oldUserId = updatingUser.getId();
        userDAO.update(updatingUser);

        return userDAO.findByUserId(oldUserId);

    }
}
