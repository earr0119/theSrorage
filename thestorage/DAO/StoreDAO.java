package com.thestorage.DAO;

import com.thestorage.domain.Store;
import com.thestorage.interfaces.Dao;
import com.thestorage.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements Dao<Store> {
    
    private final Connection connection;
    private final List<Store> stores;
    private SQLException lastException;

    public StoreDAO() {
        this.stores = new ArrayList<Store>();
        this.connection = JDBCUtils.getConnection();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    @Override
    public Store get(long id) {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from stores where id = ?")) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

               while (rs.next()) {
                    Store store = new Store();
                    store.setStoreName(rs.getString("storename"));
                    store.setStoreDistrict(rs.getString("storedistric"));
                    store.setPilotNameId(rs.getString("pilotnameid"));
                    store.setId(rs.getLong("id"));
                    this.stores.add(store);
                }

            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return this.stores.get(0);    
    }

    @Override
    public void save(Store t) {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("insert into stores(id, storename, storedistric, pilotnameid) values (?, ?, ?, ?)")) {
            preparedStatement.setLong(1, t.getId());
            preparedStatement.setString(2, t.getStoreName());
            preparedStatement.setString(3, t.getStoreDistrict());
            preparedStatement.setString(4, t.getPilotNameId());
            //preparedStatement.addBatch();
            preparedStatement.execute();
        
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;
        }
    
    }

    @Override
    public void update(Store t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Store t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Store> getAll() {
        
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from stores")) {
            System.out.println(preparedStatement);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Store store = new Store();
                    store.setStoreName(rs.getString("storename"));
                    store.setStoreDistrict(rs.getString("storedistric"));
                    store.setPilotNameId(rs.getString("pilotnameid"));
                    store.setId(rs.getLong("id"));
                    this.stores.add(store);
                }
            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;

        }
        return this.stores;
    }

    @Override
    public SQLException getLastException() {
        return lastException;
    }

 
    public List<Store> getAllFiltered(Long id, String storename) {
        
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from stores where id = ? or storename = ?")) {
            System.out.println(preparedStatement);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, storename);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Store store = new Store();
                    store.setStoreName(rs.getString("storename"));
                    store.setStoreDistrict(rs.getString("storedistric"));
                    store.setPilotNameId(rs.getString("pilotnameid"));
                    store.setId(rs.getLong("id"));
                    this.stores.add(store);
                }
            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;

        }
        return this.stores;
    }
}
