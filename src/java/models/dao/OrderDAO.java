/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import models.order.Order;
import org.apache.log4j.Logger;
import utils.DBUtils;

public class OrderDAO {
    static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    public OrderDAO() {
        
    }

    public Long insertOrder(Order order) throws SQLException, NamingException {
        Long orderID = null;
        Connection connection = DBUtils.getConnection();
        String query = "INSERT INTO tblOrders (paymentMethod, status, userID) OUTPUT Inserted.orderID VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, order.getPaymentMethod());
            preparedStatement.setString(2, order.getStatus());
            preparedStatement.setLong(3, order.getUserID());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    orderID = resultSet.getLong("orderID");
                }
            }
        }
        catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
        return orderID;
    }

    public Order getOrderById(long orderID) throws SQLException, NamingException {
        Connection connection = DBUtils.getConnection();
        Order order = null;
        String query = "SELECT orderID, orderDate, paymentMethod, status, userID FROM tblOrders WHERE orderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    order = new Order();
                    order.setOrderID(resultSet.getLong("orderID"));
                    order.setOrderDate(resultSet.getTimestamp("orderDate"));
                    order.setPaymentMethod(resultSet.getString("paymentMethod"));
                    order.setStatus(resultSet.getString("status"));
                    order.setUserID(resultSet.getLong("userID"));
                }
            }
        }
        catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
        return order;
    }
    
    public List<Order> getOrderList() throws SQLException, NamingException {
        Connection connection = DBUtils.getConnection();
        List<Order> result = new ArrayList<>();
        String query = "SELECT TOP 10 * FROM tblOrders ORDER BY orderDate DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderID(resultSet.getLong("orderID"));
                    order.setOrderDate(resultSet.getTimestamp("orderDate"));
                    order.setPaymentMethod(resultSet.getString("paymentMethod"));
                    order.setStatus(resultSet.getString("status"));
                    order.setUserID(resultSet.getLong("userID"));
                    result.add(order);
                }
            }
        }
        catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
        return result;
    }
    
    

    // Additional methods for updating and deleting orders can be added here.
}