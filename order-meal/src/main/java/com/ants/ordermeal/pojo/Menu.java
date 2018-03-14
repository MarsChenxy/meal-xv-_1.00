package com.ants.ordermeal.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wolf
 *
 * 菜单
 */
@Entity
@Table(name = "t_menu")
public class Menu {
    @Id
    @GeneratedValue
    private  Integer id;

    private  Integer userId;

    private  Integer priority;//菜单显示的顺序

    private  String menuname;//菜单名字

    private  String description;//菜单描述

    private Date createtime;//创建时间

    private Date updatetime;//修改时间

    public Menu(){             //spring-data-需要无参构造
        super();
    }
    public Menu(Integer id, Integer userId, Integer priority, String menuname, String description, Date createtime, Date updatetime) {
        this.id = id;
        this.userId = userId;
        this.priority = priority;
        this.menuname = menuname;
        this.description = description;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getDescription() {
        return description;
    }

    //请完成限制20字
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
