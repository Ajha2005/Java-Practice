package food_delivery.src.food.model;

class RegularOrder extends foodorder implements Discountable {

    RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount); //Calls the FoodOrder constructor and initializes the inherited data.
    }

    @Override //Override means: this method is already defined in the parent class, but I am providing my own version of it.
    double calculateDeliveryCharge() {
        return 80;
    }

    @Override
    public double applyDiscount() {
        return getAmount() * 0.10; //calculates the discount amount, not the final bill.
    }
}
