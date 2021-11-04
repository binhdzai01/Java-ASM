/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author hoang
 */
public class Product {
    private int proId;
    private String proName;
    private double proPrice;
    private String proDes;
    private int proView;
    private Date proCreate;
    private String proImg;
    private Category category;
    private int proSelled;
    private int proQuantity;

    public Product(int proId, String proName, double proPrice, String proDes, int proView, Date proCreate, String proImg, Category category, int proSelled,int proQuantity) {
        this.proId = proId;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proDes = proDes;
        this.proView = proView;
        this.proCreate = proCreate;
        this.proImg = proImg;
        this.category = category;
        this.proSelled = proSelled;
        this.proQuantity = proQuantity;
    }
    
    public Product() {
    }


    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }


    public String getProDes() {
        return proDes;
    }

    public void setProDes(String proDes) {
        this.proDes = proDes;
    }

    public int getProView() {
        return proView;
    }

    public void setProView(int ProView) {
        this.proView = ProView;
    }

    public Date getProCreate() {
        return proCreate;
    }

    public void setProCreate(Date proCreate) {
        this.proCreate = proCreate;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getProSelled() {
        return proSelled;
    }

    public void setProSelled(int proSelled) {
        this.proSelled = proSelled;
    }

    public int getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }
    
}
