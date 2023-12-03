package pointofsaleproject;

public class HealthProduct extends Product {

    public HealthProduct() {
    }

    @Override
    public double calculateDiscount() {
        //15% discount on health product
        return .15 * getPrice();
    }
}
