/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.order;

/**
 *
 * @author ubro3
 */
public class OrderDetail {
    private long detailID;
    private long productID;
    private long orderID;
    private int quantity;
    private double price;
    
    public OrderDetail() {
        
    }

    public OrderDetail(long detailID, long productID, long orderID, int quantity, double price) {
        this.detailID = detailID;
        this.productID = productID;
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
    }

    public long getDetailID() {
        return detailID;
    }

    public void setDetailID(long detailID) {
        this.detailID = detailID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
