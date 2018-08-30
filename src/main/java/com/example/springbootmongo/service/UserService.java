package com.example.springbootmongo.service;

import com.example.springbootmongo.bean.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    void deleteById(Integer id);

    void addUser(User user);

    User getUserById(Integer id);

    void editUserById(User user);

    Page<User> getUserPage(Integer pageOffset, Integer pageSize);
}
