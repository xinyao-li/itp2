package com.aaron.service;

import com.aaron.mapper.UserMapper;
import com.aaron.model.User;
import com.aaron.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserMapper {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }
}
