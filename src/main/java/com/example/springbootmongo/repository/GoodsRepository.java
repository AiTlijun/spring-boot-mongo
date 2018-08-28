package com.example.springbootmongo.repository;

import com.example.springbootmongo.bean.Goods;
import org.springframework.data.repository.CrudRepository;

public interface GoodsRepository extends CrudRepository<Goods,Integer> {
}
