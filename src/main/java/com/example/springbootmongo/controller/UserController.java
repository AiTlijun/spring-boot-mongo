package com.example.springbootmongo.controller;

import com.example.springbootmongo.entity.User;
import com.example.springbootmongo.entity.UserLog;
import com.example.springbootmongo.response.TableModel;
import com.example.springbootmongo.service.UserLogService;
import com.example.springbootmongo.service.jpa.UserService;
import com.example.springbootmongo.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserLogService userLogService;

    @RequestMapping("/")
    public String index() {
        return "userList";
    }

    @RequestMapping("/list")
    public String list() {
        return "userList";
    }

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public TableModel<User> getUserList(@RequestParam(required = false, defaultValue = "1") int page,
                                        @RequestParam(required = false, defaultValue = "5") int limit) {
        TableModel<User> model = new TableModel<User>();
        //List<User> users = userService.getUserList();
        System.out.println(limit);
        Page<User> userPage = userService.getUserPage(page, limit);
        model.setCode(200);
        model.setMsg("success");
        model.setServerIp(WebUtil.getServerIp());
        model.setCount((int) userPage.getTotalElements());
        model.setData(userPage.getContent());
        return model;
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp, User user) {
        try {
            userService.addUser(user);
            UserLog userLog = new UserLog();
            userLog.setUsername("admin");
            userLog.setOperation("add user");
            userLog.setOperationdate(new Date());
            userLogService.insert(userLog);
            WebUtil.writeStrToClient(resp, "200");
        } catch (Exception e) {
            WebUtil.writeStrToClient(resp, "-1");
        }
        return;
    }

    @RequestMapping("/delete")
    public void delete(Integer id, HttpServletResponse resp) {
        try {
            userService.deleteById(id);
            WebUtil.writeStrToClient(resp, "200");
        } catch (Exception e) {
            WebUtil.writeStrToClient(resp, "-1");
        }
        return;
    }

    @RequestMapping("/edit")
    public void edit(HttpServletRequest req, Integer id, User user, HttpServletResponse resp) {
        try {
            userService.editUserById(user);
            WebUtil.writeStrToClient(resp, "200");
        } catch (Exception e) {
            WebUtil.writeStrToClient(resp, "-1");
        }
        return;
    }

    @RequestMapping(value = "/getUserById")
    @ResponseBody
    public User getUserById(HttpServletRequest req, Integer id) {
        User user = userService.getUserById(id);
        return user;
    }

    @RequestMapping(value = "/deleteBath")
    @ResponseBody
    public void deleteBath(@RequestParam("brandIds") List<Integer> brandIds, HttpServletResponse resp) {
        try {
            userService.deleteBath(brandIds);
            WebUtil.writeStrToClient(resp, "200");
        } catch (Exception e) {
            WebUtil.writeStrToClient(resp, "-1");
        }
        return;
    }
}
