package com.example.springbootmongo.controller;


import com.example.springbootmongo.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("serverIp", WebUtil.getServerIp());
        System.out.println("--------serverIp------>"+model);
        return "userList";
    }
}
