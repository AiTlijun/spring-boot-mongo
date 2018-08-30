package com.example.springbootmongo.serviceImpl;

import com.example.springbootmongo.bean.User;
import com.example.springbootmongo.repository.UserRepository;
import com.example.springbootmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        List<User> userList = (List<User>) userRepository.findAll();
        return userList;
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public void editUserById(User user) {
        //User oldUser = userRepository.findById(user.getId()).get();
        userRepository.save(user);
    }

    @Override
    public Page<User> getUserPage(Integer pageOffset, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageOffset - 1, pageSize, Sort.Direction.ASC, "id");
        Page<User> pageUser = userRepository.findAll(pageable);
        return pageUser;
    }
}
