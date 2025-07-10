package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 用户登录
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        System.out.println("🔍 Received login request: " + username + " / " + password);
        User user = userRepository.findByName(username);
        System.out.println("🔎 Found user: " + user);

        if (user != null) {
            System.out.println("Stored password: " + user.getPassword());
            System.out.println("Password match: " + user.getPassword().equals(password));
        }

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

    @DeleteMapping("/delete-test")
    public String deleteTestUsers() {
        List<User> kobes = userRepository.findAll()
                .stream()
                .filter(u -> "Kobe".equals(u.getName()) && "123456".equals(u.getPassword()))
                .collect(Collectors.toList());
        userRepository.deleteAll(kobes);
        return "Deleted test Kobe users.";
    }

}
