package com.ecommerce.service;

import com.ecommerce.model.User;

public interface UserService {

    void saveUser(User user);

    void updateUser(long id, User updateUser);

    void deleteUser(User user);

    User getUser(long id);

}
