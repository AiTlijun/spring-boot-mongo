package com.example.springbootmongo.service;

import com.example.springbootmongo.bean.User;
import com.example.springbootmongo.config.datasource.TargetDataSource;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    @TargetDataSource("ds1")
    List<User> getUserList();

    @TargetDataSource("ds1")
    void deleteById(Integer id);

    @TargetDataSource("ds1")
    void addUser(User user);

    @TargetDataSource("ds1")
    User getUserById(Integer id);

    @TargetDataSource("ds1")
    void editUserById(User user);

    @TargetDataSource("ds1")
    Page<User> getUserPage(Integer pageOffset, Integer pageSize);

    @TargetDataSource("ds1")
    void deleteBath(List<Integer> brandIds);
}
