package com.example.springbootmongo.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "experience")
    private int experience;
    @Column(name = "sex")
    private byte sex;
    @Column(name = "score")
    private int score;
    @Column(name = "city")
    private String city;
    @Column(name = "sign")
    private String sign;
    @Column(name = "classify")
    private String classify;
    @Column(name = "wealth")
    private Long wealth;

    public User() {
    }
}
