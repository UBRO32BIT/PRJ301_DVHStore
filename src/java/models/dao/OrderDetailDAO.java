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
import models.order.OrderDetail;
import org.apache.log4j.Logger;
import utils.DBUtils;

public class OrderDetailDAO {
    static final Logger LOGGER = Logger.getLogger(OrderDetailDAO.class);
    public OrderDetailDAO() {
        
    }

    public void insertOrderDetail(OrderDetail orderDetail) throws SQLException, NamingException {
        Connection connection = DBUtils.getConnection();
        String query = "INSERT INTO tblOrderDetails (productID, orderID, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderDetail.getProductID());
            preparedStatement.setLong(2, orderDetail.getOrderID());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setDouble(4, orderDetail.getPrice());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
    }

    public List<OrderDetail> getOrderDetailsByOrderID(long orderID) throws SQLException, NamingException {
        Connection connection = DBUtils.getConnection();
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT detailID, productID, quantity, price FROM tblOrderDetails WHERE orderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setDetailID(resultSet.getLong("detailID"));
                    orderDetail.setProductID(resultSet.getLong("productID"));
                    orderDetail.setQuantity(resultSet.getInt("quantity"));
                    orderDetail.setPrice(resultSet.getDouble("price"));
                    orderDetails.add(orderDetail);
                }
            }
        }
        catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
        return orderDetails;
    }

    // Additional methods for updating and deleting order details can be added here.
}