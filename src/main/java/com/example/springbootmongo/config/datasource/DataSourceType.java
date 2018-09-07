package com.example.springbootmongo.config.datasource;


import lombok.Getter;

public enum DataSourceType {
    master("master", "master"), slave("slave", "slave"), slave1("slave", "slave1"), slave2("slave", "slave2");
    @Getter
    private String type;
    @Getter
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
