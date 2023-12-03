package pointofsaleproject;

public class CosmeticProduct extends Product {

    public CosmeticProduct() {
    }

    @Override
    public double calculateDiscount() {
        //5% discount on cosmetic product
        return .05 * getPrice();
    }
}
