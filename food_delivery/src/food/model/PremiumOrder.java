package food_delivery.src.food.model;

class PremiumOrder extends foodorder implements Discountable {

    PremiumOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    double calculateDeliveryCharge() {
        return 50;
    }

    @Override
    public double applyDiscount() {
        return getAmount() * 0.15;
    }
}