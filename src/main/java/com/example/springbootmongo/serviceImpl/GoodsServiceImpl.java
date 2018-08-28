package com.example.springbootmongo.serviceImpl;

import com.example.springbootmongo.bean.Goods;
import com.example.springbootmongo.repository.GoodsRepository;
import com.example.springbootmongo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<Goods> getGoodsList() {
        List<Goods> list = (List<Goods>) goodsRepository.findAll();
        return list;
    }

    @Override
    public void deletete(Integer id) {
        goodsRepository.deleteById(id);

    }

    @Override
    public void add(Goods goods) {
        goodsRepository.save(goods);
    }

    @Override
    public Goods getGoodsByid(Integer id) {
        Goods goods =  goodsRepository.findById(id).get();
        return  goods;
    }

    @Override
    public Goods editGoodsById(Integer id) {
        Goods goods =  goodsRepository.findById(id).get();
        return goods;

    }
}
