package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 用户登录
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return "登录成功，欢迎" + username;
        } else {
            return "用户名或密码错误";
        }
    }

    // 创建新用户
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // 获取所有用户（用于测试）
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
