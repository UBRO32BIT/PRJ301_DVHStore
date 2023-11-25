/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.order;
import java.util.Date;
/**
 *
 * @author ubro3
 */
public class Order {
    private long orderID;
    private Date orderDate;
    private String paymentMethod;
    private String status;
    private long userID;
    
    public Order() {
        
    }

    public Order(long orderID, Date orderDate, String paymentMethod, String status, long userID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.userID = userID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
