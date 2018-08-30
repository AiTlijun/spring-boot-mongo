package com.example.springbootmongo.controller;

import com.example.springbootmongo.bean.User;
import com.example.springbootmongo.response.BhResponseResult;
import com.example.springbootmongo.response.TableModel;
import com.example.springbootmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
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
        Page<User> userPage =  userService.getUserPage(page,limit);
        model.setCode(200);
        model.setMsg("success");
        model.setCount((int)userPage.getTotalElements());
        model.setData(userPage.getContent());
        System.out.println(userPage.getSize());
        return model;
    }

    @RequestMapping("/add")
    @Transactional
    public ModelAndView add(HttpServletRequest req, HttpServletResponse resp, User user) {
        userService.addUser(user);
        return new ModelAndView("redirect:/user/list");
    }

    @Transactional
    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(id);
        userService.deleteById(id);
        result.put("status",1);
        return result;
    }

    @RequestMapping("/edit")
    @Transactional
    public ModelAndView edit(HttpServletRequest req, Integer id, User user) {
        //User user = userService.getUserById(id);
        /*   Goods goods = new Goods();*/
        System.out.println(user);

        userService.editUserById(user);
        return new ModelAndView("redirect:/user/list");

    }
}
