package com.example.springbootmongo.repository;

import com.example.springbootmongo.bean.Goods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodsRepository extends CrudRepository<Goods,Integer>, PagingAndSortingRepository<Goods,Integer> {
}
