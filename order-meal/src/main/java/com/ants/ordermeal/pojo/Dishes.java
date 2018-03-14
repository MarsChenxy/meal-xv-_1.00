package com.ants.ordermeal.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by wolf
 *
 * 菜单信息：菜
 */
@Entity
@Table(name = "t_dishes" )//schema="SIMULATE"
public class Dishes {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer menuid;//菜单id

    private String dishname;//菜名
    //此处需要控制
    private BigDecimal privce;
    //此处需要控制
    private double discount;//折扣是未来以后店家动态调整售价

    private String photo;//图片的类型是什么暂定String 或者直接改成url

    private Integer salesNum;//月销量

    public Dishes(){
        super();
    }

    public Dishes(Integer menuid, String dishname, BigDecimal privce, double discount, String photo, Integer salesNum) {
        this.menuid = menuid;
        this.dishname = dishname;
        this.privce = privce;
        this.discount = discount;
        this.photo = photo;
        this.salesNum = salesNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public BigDecimal getPrivce() {
        return privce;
    }

    public void setPrivce(BigDecimal privce) {
        this.privce = privce;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }
}
