package com.thestorage.DAO;

import com.thestorage.domain.Order;
import com.thestorage.interfaces.Dao;
import com.thestorage.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO implements Dao<Order> {

    private final Connection connection;
    private final List<Order> orders;
    private SQLException lastException;

    public OrderDAO() {
        this.connection = JDBCUtils.getConnection();
        this.orders = new ArrayList<Order>();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    @Override
    public Order get(long id) {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where id = ?")) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getLong("orderId"));
                    order.setOrderLineItem(rs.getString("orderLineItem"));
                    order.setBillToName(rs.getString("billToName"));
                    order.setBillToAddress1(rs.getString("billToAddress1"));
                    order.setBillToAddress1(rs.getString("billToAddress2"));
                    order.setZipcode(rs.getInt("zipcode"));
                    order.setOrderCreateDttm(rs.getDate("orderCreateDttm"));
                    order.setBillToStore(rs.getString("billToStore"));
                    order.setOrderStatus(rs.getString("orderStatus"));
                    this.orders.add(order);
                }

            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return this.orders.get(0);
    }

    @Override
    public List<Order> getAll() {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from orders")) {
            System.out.println(preparedStatement);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getLong("orderId"));
                    order.setOrderLineItem(rs.getString("orderLineItem"));
                    order.setBillToName(rs.getString("billToName"));
                    order.setBillToAddress1(rs.getString("billToAddress1"));
                    order.setBillToAddress1(rs.getString("billToAddress2"));
                    order.setZipcode(rs.getInt("zipcode"));
                    order.setOrderCreateDttm(rs.getDate("orderCreateDttm"));
                    order.setBillToStore(rs.getString("billToStore"));
                    order.setOrderStatus(rs.getString("orderStatus"));
                    this.orders.add(order);
                }
            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;

        }
        return this.orders;
    }

    @Override
    public void save(Order t) {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("insert into orders(orderId, orderLineItem, billToName, billToAddress1, "
                        + "billToAddress2, zipcode, orderCreateDttm, billToStore, orderStatus  values (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setLong(1, t.getOrderId());
            preparedStatement.setString(2, t.getOrderLineItem());
            preparedStatement.setString(3, t.getBillToName());
            preparedStatement.setString(4, t.getBillToAddress1());
            preparedStatement.setString(5, t.getBillToAddress2());
            preparedStatement.setInt(6, t.getZipcode());
            preparedStatement.setDate(7, t.getOrderCreateDttm());
            preparedStatement.setString(8, t.getBillToStore());
            preparedStatement.setString(8, t.getOrderStatus());
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;
        }
    }

    @Override
    public void update(Order t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Exception getLastException() {
        return lastException;
    }

    public List<Order> getAllFiltered(Long orderId) {

        try (
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from stores where id = ?")) {
            System.out.println(preparedStatement);
            preparedStatement.setLong(1, orderId);
            try ( ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getLong("orderId"));
                    order.setOrderLineItem(rs.getString("orderLineItem"));
                    order.setBillToName(rs.getString("billToName"));
                    order.setBillToAddress1(rs.getString("billToAddress1"));
                    order.setBillToAddress1(rs.getString("billToAddress2"));
                    order.setZipcode(rs.getInt("zipcode"));
                    order.setOrderCreateDttm(rs.getDate("orderCreateDttm"));
                    order.setBillToStore(rs.getString("billToStore"));
                    order.setOrderStatus(rs.getString("orderStatus"));
                    this.orders.add(order);
                }
            }
            preparedStatement.close();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            this.lastException = e;

        }
        return this.orders;
    }
}
