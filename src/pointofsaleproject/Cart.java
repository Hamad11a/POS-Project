package pointofsaleproject;

public class Cart {

    private Product product;
    private int qty;

    public Cart(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return this.qty;
    }
}
