package com.example.springbootmongo.controller;

import com.example.springbootmongo.bean.Goods;
import com.example.springbootmongo.response.TableModel;
import com.example.springbootmongo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        return "index";
    }

    @RequestMapping("/getGoodsList")
    @ResponseBody
    public TableModel getGoodsList(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "5") int limit) {
        TableModel<Goods> model = new TableModel<Goods>();
        List<Goods> goods = goodsService.getGoodsList();
        model.setCode(200);
        model.setMsg("success");
        model.setCount(goods.size());
        model.setData(goods);
       /* result.("code",200);
        result.put("msg","SUCCESS");
        result.put("count",goods.size());
        result.put("data",goods);*/
        return model;
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
        return new ModelAndView("redirect:/goods/list");

    }

    @RequestMapping("/deletete")
    public ModelAndView delete(Integer id, Model m) {
        System.out.println(id);
        goodsService.deletete(id);
        return new ModelAndView("redirect:/goods/list");
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
        return new ModelAndView("redirect:/goods/list");

    }
}
