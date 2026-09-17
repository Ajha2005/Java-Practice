package food_delivery.src.food.model;

abstract class foodorder {       //FoodOrder is a template/parent class, so we make it abstract.
   private int orderId;          //access them using getters/setters.
   private String customerName;
   private double amount;
   
   static String restaurantName;  //This is shared by all orders.
   static int ordercount =0;       //keeps track of how many orders have been created.

   foodorder(int orderId, String customerName, double amount) {
       this.orderId = orderId;
       this.customerName = customerName;
       this.amount = amount; //Store the given amount inside this object's amount variable
       ordercount++;
   }
    public int getOrderId() { //Give me this order's orderId.
        return orderId;
    }

    public void setOrderId(int orderId) { //Change this order's orderId.
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    abstract double calculateDeliveryCharge(); // abstract method: telling the child classes: you MUST tell me your delivery charge.
    public static void displayOrderCount() {
        System.out.println("Total orders placed: " + ordercount);
    }
}

