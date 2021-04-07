package com.ecommerce.service.impl;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;

import javax.inject.Inject;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(long id, User updateUser) {
        User oldUser = getUser(id);
        oldUser.setUserName(updateUser.getUserName());
        oldUser.setPassword(updateUser.getPassword());
        oldUser.setEmail(updateUser.getEmail());
        saveUser(updateUser);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUser(long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found id:" + id);
        }
        return user;
    }
}
