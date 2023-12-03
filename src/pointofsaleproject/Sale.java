package pointofsaleproject;

import java.util.ArrayList;


public class Sale {
    
    private ArrayList<Cart> cartItems;
    private String customerName;
    private double totalAmount;
    private double totalDiscount;
    
    
    public ArrayList<Cart> getCartItems() {
        return cartItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setCartItems(ArrayList<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}

