package pointofsaleproject;

public class SportProduct extends Product {

    public SportProduct() {
    }

    @Override
    public double calculateDiscount() {
        //10% discount on sports product
        return .10 * getPrice();
    }
}
