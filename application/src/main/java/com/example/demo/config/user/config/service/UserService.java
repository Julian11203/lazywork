package com.example.demo.config.user.config.service;

import com.example.demo.config.user.config.dto.SaveUser;
import com.example.demo.config.user.config.entity.User;

public interface UserService {
    User registerOneCustomer(SaveUser newUser);
}
