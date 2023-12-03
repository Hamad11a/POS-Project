package pointofsaleproject;
public class Product implements Discountable {

    private String productType;
    private String name;
    private double price;
    private int inventoryLevel;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        if (productType.equals("Cosmetic") || productType.equals("Sports") || productType.equals("Health")) {
            this.productType = productType;
        } else {
            System.out.println("The type should be \"Cosmetic\" or \"Sports\" \"Health\"");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInventoryLevel(int lnventoryLevel) {
        this.inventoryLevel = lnventoryLevel;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getInventoryLevel() {
        return this.inventoryLevel;
    }

    @Override
    public double calculateDiscount() {
        return 0 * price;
    }

}
