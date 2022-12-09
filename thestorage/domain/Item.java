package com.thestorage.domain;

import com.thestorage.DAO.*;
import com.thestorage.utils.JDBCUtils;
import java.sql.Connection;
import java.util.ArrayList;

public class Item {
    
    private Integer storeID;
    private Integer id;
    private String itemName;
    private Double itemWeight;
    private Float itemValue;
    private ArrayList<Item> items;
    private Connection connection;

    public Item() {
    }

    public Item(Integer storeID, Integer id, String itemName, Double itemWeight, Float itemValue, ArrayList<Item> items, Connection connection) {
        this.storeID = storeID;
        this.id = id;
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemValue = itemValue;
        this.items = items;
        this.connection = connection;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeId) {
        this.storeID = storeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Float getItemValue() {
        return itemValue;
    }

    public void setItemValue(Float itemValue) {
        this.itemValue = itemValue;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    

    
}
