package com.ecommerce.service;

import com.ecommerce.dto.UserDto;
import com.ecommerce.model.User;

public interface UserService {

    void saveUser(UserDto userDto);

    void updateUser(long id, User updateUser);

    void deleteUser(User user);

    User getUser(long id);

}
