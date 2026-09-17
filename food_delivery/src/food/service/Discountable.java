package food_delivery.src.food.service;

interface Discountable {

    double applyDiscount();  //Any class that implements Discountable MUST provide an applyDiscount() method.
}

//the interface only defines what method must exist, while each class decides how it works