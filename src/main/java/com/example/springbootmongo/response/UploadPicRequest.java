package com.example.springbootmongo.response;

import com.example.springbootmongo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class UploadPicRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<File> pics;
    private List<User> users;

    public List<File> getPics() {
        return pics;
    }

    public void setPics(List<File> pics) {
        this.pics = pics;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
