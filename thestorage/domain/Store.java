package com.thestorage.domain;

import com.thestorage.DAO.*;
import com.thestorage.utils.JDBCUtils;
import java.sql.Connection;
import java.util.ArrayList;

public class Store {
    
    private String storeName;
    private String storeDistrict;
    private String pilotNameId;
    private ArrayList<Item> items;
    private Connection connection;
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Store() {
    }
    

    public Store(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Store(String storeName, String storeDistrict, String pilotNameId) {
        this.storeName = storeName;
        this.storeDistrict = storeDistrict;
        this.pilotNameId = pilotNameId;
        this.connection = JDBCUtils.getConnection();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDistrict() {
        return storeDistrict;
    }

    public void setStoreDistrict(String storeDistrict) {
        this.storeDistrict = storeDistrict;
    }

    public String getPilotNameId() {
        return pilotNameId;
    }

    public void setPilotNameId(String pilotNameId) {
        this.pilotNameId = pilotNameId;
    }
}
