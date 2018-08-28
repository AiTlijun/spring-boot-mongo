package com.example.springbootmongo.service;

import com.example.springbootmongo.bean.Goods;

import java.util.List;

public interface GoodsService {
    public List<Goods> getGoodsList();

    public void deletete(Integer id);

    public void add(Goods goo);

    public Goods getGoodsByid(Integer id);

    public  Goods editGoodsById(Integer id);
}
