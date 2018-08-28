package com.example.springbootmongo.bean;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Person {

    @Id
    private Long id;
    private String userName;

}
