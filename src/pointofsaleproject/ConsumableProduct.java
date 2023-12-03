package pointofsaleproject;

public class ConsumableProduct extends Product{

    public ConsumableProduct() {
    }

    @Override
    public double calculateDiscount() {
        //20% discount on consumable product
        return .20 * getPrice();
    }
}
