package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 你也可以自定义查询，比如通过邮箱找用户：
    User findByEmail(String email);
}
