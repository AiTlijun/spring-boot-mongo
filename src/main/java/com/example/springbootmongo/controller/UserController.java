package com.example.springbootmongo.controller;

import com.example.springbootmongo.bean.Goods;
import com.example.springbootmongo.bean.User;
import com.example.springbootmongo.response.TableModel;
import com.example.springbootmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String index() {
        return "userList";
    }

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public TableModel<User> getUserList(@RequestParam(required = false, defaultValue = "1") int page,
                                        @RequestParam(required = false, defaultValue = "5") int limit) {
        TableModel<User> model = new TableModel<User>();
        List<User> users = userService.getUserList();
        model.setCode(200);
        model.setMsg("success");
        model.setCount(users.size());
        model.setData(users);
        return model;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        String userName = req.getParameter("userName");
        String experience = req.getParameter("experience");
        String sex = req.getParameter("sex");
        String score = req.getParameter("score");
        String city = req.getParameter("city");
        String sign = req.getParameter("sign");
        String classify = req.getParameter("classify");
        String wealth = req.getParameter("wealth");
        user.setUsername(userName);
        user.setExperience(Integer.parseInt(experience));
        user.setSex(Byte.parseByte(sex));
        user.setScore(Integer.parseInt(score));
        user.setCity(city);
        user.setSign(sign);
        user.setClassify(classify);
        user.setWealth(Long.parseLong(wealth));
        userService.addUser(user);
        return new ModelAndView("redirect:/user/list");
    }

    @RequestMapping("/deletete")
    public ModelAndView delete(Integer id, Model m) {
        System.out.println(id);
        userService.deleteById(id);
        return new ModelAndView("redirect:/user/list");
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest req, Integer id, User user) {
        //User user = userService.getUserById(id);
        /*   Goods goods = new Goods();*/
        System.out.println(user);

        userService.editUserById(user);
        return new ModelAndView("redirect:/user/list");

    }
}
