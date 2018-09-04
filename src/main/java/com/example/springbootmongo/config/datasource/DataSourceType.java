package com.example.springbootmongo.config.datasource;


import lombok.Getter;

public enum DataSourceType {
    read("read", "slave"), write("write", "master");
    @Getter
    private String type;
    @Getter
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
