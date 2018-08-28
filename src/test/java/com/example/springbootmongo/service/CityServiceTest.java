package com.example.springbootmongo.service;

import com.example.springbootmongo.bean.City;
import com.example.springbootmongo.repository.CityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CityServiceTest {
    @Autowired
    private CityRepository cityRepository;

    @Test
    private void addCity(){
        cityRepository.save(new City());
    }
}