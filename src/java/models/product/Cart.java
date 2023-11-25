/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.product;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ubro3
 */
public class Cart {
    private Map<Long, Product> cart;

    public Cart() {
    }

    public Cart(Map<Long, Product> cart) {
        this.cart = cart;
    }

    public Map<Long, Product> getCart() {
        return cart;
    }

    public void setCart(Map<Long, Product> cart) {
        this.cart = cart;
    }
    
    public void add(Product product) {
        if (this.cart == null) {
            this.cart = new HashMap<Long, Product>();
        }
        if (this.cart.containsKey(product.getProductID())) {
            int currentQuantity = this.cart.get(product.getProductID()).getQuantity();
            product.setQuantity(currentQuantity + product.getQuantity());
        }
        cart.put(product.getProductID(), product);
    }
    
    public boolean remove(Long id) {
        boolean result = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.remove(id);
                result = true;
            }
        }
        return result;
    }
    public boolean edit(Long id, Product newProduct) {
        boolean result = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, newProduct);
                result = true;
            }
        }
        return result;
    }
}
