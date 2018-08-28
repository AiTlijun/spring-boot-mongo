package com.example.springbootmongo.repository;

import com.example.springbootmongo.bean.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CityRepository extends JpaRepository<City,Integer> {
}
