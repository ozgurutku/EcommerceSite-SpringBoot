package com.ecommerce.service.impl;

import com.ecommerce.dto.UserDto;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.Role;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private CartRepository cartRepository;

    @Inject
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList(new Role("USER")));
        Cart cart = new Cart();
        List<Product> product = new ArrayList<>();
        cart.setProduct(product);
        cart.setUser(user);
        userRepository.save(user);
        cartRepository.save(cart);
    }

    @Override
    public void updateUser(long id, User updateUser) {
        User oldUser = getUser(id);
        oldUser.setName(updateUser.getName());
        oldUser.setPassword(updateUser.getPassword());
        oldUser.setEmail(updateUser.getEmail());
        //saveUser(updateUser);
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
