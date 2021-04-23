package com.ecommerce.controller;

import com.ecommerce.dto.UserDto;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller()
@RequestMapping("/registration")
public class UserController {
    @Inject
    private UserService userService;

    @ModelAttribute("user")
    public UserDto user() {
        return new UserDto();
    }

    @GetMapping
    public String getRegistrationPage() {
        return "register.html";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto userDto) {
        try {
            userService.saveUser(userDto);
        }catch (Exception constraintViolationException){
            return "redirect:/registration?error";
        }
        return "redirect:/registration?success";
    }
}
