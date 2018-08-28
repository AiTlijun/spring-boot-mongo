package com.example.springbootmongo.controller;

import com.example.springbootmongo.bean.Goods;
import com.example.springbootmongo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model m) {
        m.addAttribute("goods", goodsService.getGoodsList());
        return "index";
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest req, Model m) {
        Goods goo1 = new Goods();
        String goodsName = req.getParameter("name");
        String brand = req.getParameter("brand");
        String price = req.getParameter("price");
        String place = req.getParameter("place");
        goo1.setGoodsName(goodsName);
        goo1.setGoodsBrand(brand);
        goo1.setGoodsPrice(price);
        goo1.setGoodsPlace(place);
        goodsService.add(goo1);
        return new ModelAndView("redirect:/list");

    }

    @RequestMapping("/deletete")
    public ModelAndView delete(Integer id, Model m) {
        System.out.println(id);
        goodsService.deletete(id);
        return new ModelAndView("redirect:/list");
    }

    @RequestMapping("/allGoods")
    public ModelAndView allGoods(Integer id, Model m) {
        Goods goods = goodsService.getGoodsByid(id);
        System.out.println(goods);
        m.addAttribute("goods", goods);
        return new ModelAndView("allGoods");
    }

    @RequestMapping("/eidtGoodsAll")
    public ModelAndView eidtGoodsAll(Integer id, Model m) {
        Goods goods = goodsService.getGoodsByid(id);
        System.out.println(goods);
        m.addAttribute("goods", goods);
        return new ModelAndView("editGoods");
    }

    @RequestMapping("/editGoods")
    public ModelAndView editGoods(HttpServletRequest req, Integer id, Model m) {
        Goods goods = goodsService.getGoodsByid(id);
        /*   Goods goods = new Goods();*/
        System.out.println(goods);
        goods.setGoodsName(req.getParameter("name"));
        goods.setGoodsPrice(req.getParameter("price"));
        goods.setGoodsPlace(req.getParameter("place"));
        goods.setGoodsBrand(req.getParameter("brand"));
        m.addAttribute("goods", goods);
        goodsService.add(goods);
        return new ModelAndView("redirect:/list");

    }
}
