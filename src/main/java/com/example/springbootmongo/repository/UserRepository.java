package com.example.springbootmongo.repository;

import com.example.springbootmongo.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User,Integer>, PagingAndSortingRepository<User,Integer> {
}
