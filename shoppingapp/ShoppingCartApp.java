import exceptions.*;
import model.Product;
import java.util.*;

public class ShoppingCartApp {

    static Map<String, Product> catalog = new HashMap<>();
    static Map<String, Integer> cart = new HashMap<>();

    public static void main(String[] args) {

        catalog.put("pen", new Product("pen", 10.0, 5));
        catalog.put("book", new Product("book", 250.0, 2));
        catalog.put("bag", new Product("bag", 800.0, 3));

        try {
            searchProduct("pen");

            addToCart("pen", 2);

            addToCart("book", 1);

            removeFromCart("book");

            addToCart("bag", 1);

            double total = checkout(1000.0);

            System.out.println("Payment successful.");
            System.out.println("Total amount = " + total);

        } catch (ProductException e) {
            System.out.println("Product problem: " + e.getMessage());

        } catch (PaymentException e) {
            System.out.println("Payment problem: " + e.getMessage());

        } catch (OrderException e) {
            System.out.println("Order problem: " + e.getMessage());

        } catch (ApplicationException e) {
            System.out.println("Application error: " + e.getMessage());
        }
    }

    static void searchProduct(String productName)
            throws ProductNotFoundException {

        Product p = catalog.get(productName);

        if (p == null) {
            throw new ProductNotFoundException(
                    "Product not found: " + productName);
        }

        System.out.println("Product found:");
        System.out.println("Name = " + p.getName());
        System.out.println("Price = " + p.getPrice());
        System.out.println("Stock = " + p.getStock());
    }

    static void addToCart(String productName, int qty)
            throws ProductException {

        Product p = catalog.get(productName);

        if (p == null) {
            throw new ProductNotFoundException(
                    "Product not found: " + productName);
        }

        if (p.getStock() < qty) {
            throw new OutOfStockException(
                    productName + " is out of stock.");
        }

        cart.put(productName,
                cart.getOrDefault(productName, 0) + qty);

        p.reduceStock(qty);

        System.out.println(
                "Added " + qty + " x " + productName + " to cart.");
    }

    static void removeFromCart(String productName)
            throws ProductNotFoundException {

        if (!cart.containsKey(productName)) {
            throw new ProductNotFoundException(
                    productName + " is not present in cart.");
        }

        cart.remove(productName);

        System.out.println(
                productName + " removed from cart.");
    }

    static double checkout(double amountPaid)
            throws OrderException, PaymentException {

        if (cart.isEmpty()) {
            throw new EmptyCartException(
                    "Cannot checkout - cart is empty.");
        }

        double total = 0;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {

            String productName = entry.getKey();
            int quantity = entry.getValue();

            Product product = catalog.get(productName);

            total += product.getPrice() * quantity;
        }

        if (amountPaid < total) {
            throw new InsufficientFundsException(
                    "Paid " + amountPaid +
                    " but total is " + total);
        }

        return total;
    }
}