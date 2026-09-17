package food_delivery.src.food.utility;

import food_delivery.src.food.model.foodorder;
import food_delivery.src.food.service.Discountable;

class OrderUtility {

    public static boolean validateAmount(double amount) { //returns true if the amount is greater than 0, otherwise false
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty(); //returns true if the name is not null and not empty, otherwise false
    }

    public static void generateOrderSummary(foodorder order) { //We're accepting a FoodOrder reference.That means we can pass either: RegularOrder or:PremiumOrder
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Amount: " + order.getAmount());
        System.out.println("Delivery Charge: " + order.calculateDeliveryCharge());  //automatically calls the appropriate version: regular or premium. This is polymorphism + method overriding working together.
        System.out.println("Discount: " + ((Discountable) order).applyDiscount()); //type casting: we are telling the compiler: "I know this order is a Discountable, so let me call the applyDiscount() method on it." This is safe because both RegularOrder and PremiumOrder implement Discountable.
    }
}
