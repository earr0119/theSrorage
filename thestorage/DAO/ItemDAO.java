package com.thestorage.DAO;

import com.thestorage.domain.Item;
import com.thestorage.interfaces.Dao;
import com.thestorage.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements Dao<Item> {

    private final Connection connection;
    private SQLException lastException;
    private final ArrayList<Item> items;

    public ItemDAO() {
        this.items = new ArrayList<Item>();
        this.connection = JDBCUtils.getConnection();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    @Override
    public Item get(long l) {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from items where id = ?")) {
            preparedStatement.setInt(1, (int) l);
            System.out.println(preparedStatement);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Item item = new Item();
                    item.setStoreID(rs.getInt("storeID"));
                    item.setId(rs.getInt("id"));
                    item.setItemName(rs.getString("itemeName"));
                    item.setItemWeight(rs.getDouble("itemWeight"));
                    item.setItemValue(rs.getFloat("itemValue"));
                    this.items.add(item);
                }

            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return this.items.get(0);
    }

    public List<Item> getAllFilterItem() { //throw new UnsupportedOperationException("Not supported yet."); 
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from items")) {
            System.out.println(preparedStatement);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Item item = new Item();
                    item.setStoreID(rs.getInt("storeID"));
                    item.setId(rs.getInt("id"));
                    item.setItemName(rs.getString("itemeName"));
                    item.setItemWeight(rs.getDouble("itemWeight"));
                    item.setItemValue(rs.getFloat("itemValue"));

                    this.items.add(item);
                }
            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;

        }
        return this.items;

    }

    @Override
    public void save(Item t) {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("insert into items(storeID, id, itemName, itemWeight, itemValue) values (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, t.getStoreID());
            preparedStatement.setInt(2, t.getId());
            preparedStatement.setString(3, t.getItemName());
            preparedStatement.setDouble(4, t.getItemWeight());
            preparedStatement.setFloat(5, t.getItemValue());
            

            //preparedStatement.addBatch();
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;
        }
    }

    @Override
    public void update(Item t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Item t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Exception getLastException() {
        return lastException;
    }

    @Override
    public List<Item> getAll() {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from items")) {
            System.out.println(preparedStatement);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Item item = new Item();
                    item.setStoreID(rs.getInt("storeID"));
                    item.setId(rs.getInt("id"));
                    item.setItemName(rs.getString("itemName"));
                    item.setItemWeight(rs.getDouble("itemWeight"));
                    item.setItemValue(rs.getFloat("itemValue"));
                    this.items.add(item);
                }
            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;

        }
        return this.items;
    }

    public List<Item> getAllItemFilter(Integer id) {

        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from items where id = ? or 0 = ?")) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Item item = new Item();
                    item.setStoreID(rs.getInt("storeID"));
                    item.setId(rs.getInt("id"));
                    item.setItemName(rs.getString("itemName"));
                    item.setItemWeight(rs.getDouble("itemWeight"));
                    item.setItemValue(rs.getFloat("itemValue"));
                    this.items.add(item);
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;

        }
        return this.items;
    }
}
