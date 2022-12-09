package com.thestorage.domain;

import com.thestorage.DAO.*;
import java.sql.Date;
import java.util.ArrayList;

public class Order {

    private long orderId;
    private String orderLineItem;
    private String billToName;
    private String billToAddress1;
    private String billToAddress2;
    private Integer zipcode;
    private Date orderCreateDttm;
    private String billToStore;
    private String orderStatus;
    private ArrayList<Order> orders;

    public Order() {
    }

    public Order(long orderId, String orderLineItem, String billToName, String billToAddress1, String billToAddress2, Integer zipcode, Date orderCreateDttm, String billToStore, String orderStatus, ArrayList<Order> orders) {
        this.orderId = orderId;
        this.orderLineItem = orderLineItem;
        this.billToName = billToName;
        this.billToAddress1 = billToAddress1;
        this.billToAddress2 = billToAddress2;
        this.zipcode = zipcode;
        this.orderCreateDttm = orderCreateDttm;
        this.billToStore = billToStore;
        this.orderStatus = orderStatus;
        this.orders = orders;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderLineItem() {
        return orderLineItem;
    }

    public void setOrderLineItem(String orderLineItem) {
        this.orderLineItem = orderLineItem;
    }

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    public String getBillToAddress1() {
        return billToAddress1;
    }

    public void setBillToAddress1(String billToAddress1) {
        this.billToAddress1 = billToAddress1;
    }

    public String getBillToAddress2() {
        return billToAddress2;
    }

    public void setBillToAddress2(String billToAddress2) {
        this.billToAddress2 = billToAddress2;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Date getOrderCreateDttm() {
        return orderCreateDttm;
    }

    public void setOrderCreateDttm(Date orderCreateDttm) {
        this.orderCreateDttm = orderCreateDttm;
    }

    public String getBillToStore() {
        return billToStore;
    }

    public void setBillToStore(String billToStore) {
        this.billToStore = billToStore;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
