/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author hoang
 */
public class Order {
    private int orderId;
    private Date orderDate;
    private Account account;
    private boolean orderState;
    private List<Product > proIds;
    public Order() {
    }

    public Order(int orderId, Date orderDate, Account account, boolean orderState, List<Product> proIds) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.account = account;
        this.orderState = orderState;
        this.proIds = proIds;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isOrderState() {
        return orderState;
    }

    public void setOrderState(boolean orderState) {
        this.orderState = orderState;
    }

    public List<Product> getProIds() {
        return proIds;
    }

    public void setProIds(List<Product> proIds) {
        this.proIds = proIds;
    }

    

    

   

}
