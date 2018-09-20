package com.example.springbootmongo.serviceImpl.jpa;

import com.example.springbootmongo.entity.User;
import com.example.springbootmongo.repository.UserRepository;
import com.example.springbootmongo.service.RedisService;
import com.example.springbootmongo.service.jpa.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;

    @Cacheable(value = "user", keyGenerator = "keyGenerator")
    @Override
    public List<User> getUserList() {
        List<User> userList = (List<User>) userRepository.findAll();
        return userList;
    }

    @CacheEvict(value = "user", keyGenerator = "keyGenerator")
    @Override
    public void deleteById(Integer id) {
        log.info("form DB deleteById:{}" + id);
        userRepository.deleteById(id);
    }

    @Override
    public void addUser(User user) {
        log.info("form DB addUser:{}" + user.getUserName());
        userRepository.save(user);
    }

    @Cacheable(value = "user", keyGenerator = "keyGenerator")
    @Override
    public User getUserById(Integer id) {
        log.info("form DB getUserById:{}" + id);
        User user = userRepository.findById(id).get();
        return user;
    }

    @CachePut(value = "user", keyGenerator = "keyGenerator")
    @Override
    public void editUserById(User user) {
        log.info("form DB editUserById:{}" + user.getId());
        userRepository.save(user);
    }

    @Cacheable(value = "user", keyGenerator = "keyGenerator")
    @Override
    public Page<User> getUserPage(Integer pageOffset, Integer pageSize) {
        log.info("form DB getUserPage pageOffset:{}" + pageOffset);
        PageRequest pageable = PageRequest.of(pageOffset - 1, pageSize, Sort.Direction.ASC, "id");
        Page<User> pageUser = userRepository.findAll(pageable);
        return pageUser;
    }

    @CacheEvict(value = "user", keyGenerator = "keyGenerator")
    @Override
    public void deleteBath(List<Integer> brandIds) {
        log.info("form DB deleteBath:{}" + brandIds);
        userRepository.deleteBatch(brandIds);
    }
}
