package pointofsaleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Shop {

    public static void main(String[] args) {

        List<Product> products = new ArrayList<Product>();
        List<Cart> cartItems = new ArrayList<Cart>();
        List<Sale> sales = new ArrayList<Sale>();

        ReadInitialsProducts(products);
        Scanner input = new Scanner(System.in);

        int choice;
        while (true) {
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. Clear Cart");
            System.out.println("4. View Sales");
            System.out.println("5. Add Product");
            System.out.println("6. Remove Product");
            System.out.println("7. Search for Product");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();

                switch (choice) {
                    case 1:

                        while (true) {
                            for (int i = 0; i < products.size(); i++) {
                                PrintProductInformation(i + 1, products.get(i));
                            }
                            System.out.println("Enter product number to add to cart press 0 to exit");
                            choice = input.nextInt();
                            if (choice == 0) {

                                break;
                            } else if (choice > products.size() || choice < 0) {
                                System.out.println("Invalid input");
                            } else {
                                Product product = products.get(choice - 1);
                                while (true) {
                                    System.out.println("Enter required quantity");
                                    int qty = input.nextInt();
                                    if (product.getInventoryLevel() < qty) {
                                        System.out.println("Desired quantity not available");
                                    } else if (qty < 0) {
                                        System.out.println("quantity cant be negitive");
                                    } else {
                                        products.get(choice - 1).setInventoryLevel(product.getInventoryLevel() - qty);
                                        Cart cart = new Cart(product, qty);
                                        cartItems.add(cart);
                                        System.out.println("Item added to cart successfully");
                                        break;
                                    }
                                }
                            }

                        }
                        break;
                    case 2:
                        if (cartItems.size() <= 0) {
                            System.out.println("No items in cart go to view products and add some products to cart.");
                        } else {
                            double totalAmount = 0, totalDiscount = 0;
                            for (int i = 0; i < cartItems.size(); i++) {

                                Cart cart = cartItems.get(i);
                                System.out.println(cart.getProduct().getName() + " | " + cart.getProduct().getPrice() + " | " + cart.getQty());
                                totalAmount += cart.getProduct().getPrice() * cart.getQty();
                                totalDiscount += cart.getProduct().calculateDiscount() * cart.getQty();
                            }
                            System.out.println("Subtotal.   : " + totalAmount);
                            System.out.println("Discount.   : " + totalDiscount);
                            System.out.println("Net Amount. : " + (totalAmount - totalDiscount));

                            System.out.println("\nPress 1 to checkout.");
                            System.out.println("Press any key to go back previous menu");
                            choice = input.nextInt();
                            if (choice == 1) {
                                System.out.println("Enter customer name");
                                String customerName = input.next();
                                Sale sale = new Sale();
                                ArrayList<Cart> items = new ArrayList<>();
                                items.addAll(cartItems);
                                sale.setCartItems(items);
                                sale.setCustomerName(customerName);
                                sale.setTotalAmount(totalAmount);
                                sale.setTotalDiscount(totalDiscount);
                                sales.add(sale);
                                cartItems.clear();
                                System.out.println("Checkout succesfull.");
                            }
                        }
                        break;
                    case 3:
                        if (cartItems.size() > 0) {
                            cartItems.clear();
                            System.out.println("Cart cleared.");
                        } else {
                            System.out.println("No items in cart");
                        }
                        break;
                    case 4:
                        Map<String, Integer> popularProducts = new HashMap<>();
                        double totalRevenue = 0,
                         totalDiscount = 0;
                        for (Sale sale : sales) {
                            totalRevenue += sale.getTotalAmount();
                            totalDiscount += sale.getTotalDiscount();
                            for (Cart cartItem : sale.getCartItems()) {
                                String productName = cartItem.getProduct().getName();
                                int quantity = cartItem.getQty();
                                if (popularProducts.containsKey(productName)) {
                                    int newQty = quantity + popularProducts.get(productName);
                                    popularProducts.put(productName, newQty);
                                } else {
                                    popularProducts.put(productName, quantity);
                                }
                            }
                        }
                        Map.Entry<String, Integer> popular = null;
                        for (Map.Entry<String, Integer> entry : popularProducts.entrySet()) {
                            if (popular == null || entry.getValue() > popular.getValue()) {
                                popular = entry;
                            }

                        }
                        System.out.println("Total Revenue: " + (totalRevenue - totalDiscount));
                        System.out.println("Most popular product: " + popular.getKey());

                        break;
                    case 5:
                        // Add Product
                         System.out.println("The Product type should be one of these types Cosmetic, Sports, Health and Consumable");
                        System.out.println("Enter product details:");
                        System.out.print("Type: ");
                        String productType = input.next();
                        if (productType.equals("Cosmetic") || productType.equals("Sports") || productType.equals("Health") || productType.equals("Consumable")) {
                        System.out.print("Name: ");
                        String productName = input.next();
                        System.out.print("Price: ");
                        double productPrice = input.nextDouble();
                        System.out.print("Inventory Level: ");
                        int inventoryLevel = input.nextInt();
                        Product newProduct = new Product();
                        newProduct.setProductType(productType);
                        newProduct.setInventoryLevel(inventoryLevel);
                        newProduct.setPrice(productPrice);
                        newProduct.setName(productName);
                        products.add(newProduct);
                        System.out.println("Product added successfully.");
                        }
                        else
                             System.out.println("Product type is wrong");
            
                        break;
                    case 6:
                        // Remove Product
                        System.out.print("Enter the name of the product to remove: ");
                        Scanner scanner = new Scanner(System.in);
                        String productToRemove = scanner.nextLine();
                        for (int i = 0; i < products.size(); i++) {

                            Product newProduct1 = products.get(i);
                            if (newProduct1.getName().equalsIgnoreCase(productToRemove)) {
                                products.remove(i);
                                break;
                            }
                        }

                        break;
                    case 7:
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("What is the product you want to search for");
                        String SearchForProduct = scanner1.nextLine();
                        for (Product product : products) {
                            if (product.getName().equalsIgnoreCase(SearchForProduct)) {
                                System.out.print(product.getName() + " is avalibale " + ", The price is " + product.getPrice() + " and the quantity is " + product.getInventoryLevel() + "\n");
                                break;
                            } else {
                                System.out.println(SearchForProduct + " is not avalibale ");
                                break;
                            }
                        }
                        break;
                    case 8:
                        System.out.println("END!");

                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                input.next(); // Clear the invalid input from the input buffer
            }
        }

    }

    private static void ReadInitialsProducts(List<Product> products) {
        File file = new File("inventoryLevels.txt");
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] details = line.split(",");
                if (details[0].equals("Cosmetic")) {
                    CosmeticProduct product = new CosmeticProduct();
                    product.setName(details[1]);
                    product.setPrice(Double.parseDouble(details[2]));
                    product.setInventoryLevel(Integer.parseInt(details[3]));

                    products.add(product);

                } else if (details[0].equals("Sports")) {
                    SportProduct product = new SportProduct();
                    product.setName(details[1]);
                    product.setPrice(Double.parseDouble(details[2]));
                    product.setInventoryLevel(Integer.parseInt(details[3]));

                    products.add(product);
                } else if (details[0].equals("Health")) {
                    HealthProduct product = new HealthProduct();
                    product.setName(details[1]);
                    product.setPrice(Double.parseDouble(details[2]));
                    product.setInventoryLevel(Integer.parseInt(details[3]));
                    products.add(product);
                } else if (details[0].equals("Consumable")) {
                    ConsumableProduct product = new ConsumableProduct();
                    product.setName(details[1]);
                    product.setPrice(Double.parseDouble(details[2]));
                    product.setInventoryLevel(Integer.parseInt(details[3]));
                    products.add(product);
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    private static void PrintProductInformation(int index, Product product) {
        System.out.println(index + "- Name: " + product.getName() + " Price: " + product.getPrice() + " Left In Stock: " + product.getInventoryLevel());
    }

}
