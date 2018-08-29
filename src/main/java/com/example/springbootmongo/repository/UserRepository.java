package com.example.springbootmongo.repository;

import com.example.springbootmongo.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
