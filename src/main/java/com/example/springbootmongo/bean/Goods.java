package com.example.springbootmongo.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "GOODS")
public class Goods {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer  id;
    @Column(name="GOODS_NAME")
    private String goodsName;
    @Column(name="GOODS_BRAND")
    private String goodsBrand;
    @Column(name="GOODS_PRICE")
    private String goodsPrice;
    @Column(name="GOODS_PLACE")
    private String goodsPlace;

    public Goods() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public Integer getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }
}
