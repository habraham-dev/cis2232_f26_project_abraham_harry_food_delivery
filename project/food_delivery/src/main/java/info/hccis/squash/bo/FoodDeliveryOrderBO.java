package info.hccis.squash.bo;

import info.hccis.squash.jpa.entity.FoodDeliveryOrder;

/**
 * Business logic class for food delivery orders. Holds the calculations for
 * the delivery fee and the total cost of an order.
 *
 * @author Harry Abraham
 * @since 20261123
 * Issue#6
 */
public class FoodDeliveryOrderBO {

    public static final double COST_PER_KM = 1.50;

    /**
     * Calculate and set the delivery fee and total cost of the order.
     *
     * @param foodDeliveryOrder the order entered by the user
     * @return the same order with the delivery fee and total cost filled in
     * @since 20261123
     * @author Harry Abraham
     */
    public FoodDeliveryOrder processCost(FoodDeliveryOrder foodDeliveryOrder) {

        double deliveryFee = calculateDeliveryFee(foodDeliveryOrder.getDeliveryDistance());
        double totalCost = calculateTotalCost(foodDeliveryOrder.getFoodSubtotal(),
                deliveryFee, foodDeliveryOrder.getTipAmount());

        foodDeliveryOrder.setDeliveryFee(deliveryFee);
        foodDeliveryOrder.setTotalCost(totalCost);

        return foodDeliveryOrder;
    }

    /**
     * Calculate the delivery fee. Delivery Fee = Delivery Distance x $1.50
     *
     * @param deliveryDistance distance in kilometres
     * @return delivery fee
     * @since 20261123
     * @author Harry Abraham
     */
    public double calculateDeliveryFee(double deliveryDistance) {
        return deliveryDistance * COST_PER_KM;
    }

    /**
     * Calculate the total cost. Total Cost = Food Subtotal + Delivery Fee + Tip
     *
     * @param foodSubtotal price of the food
     * @param deliveryFee calculated delivery fee
     * @param tipAmount tip added by the customer
     * @return total cost of the order
     * @since 20261123
     * @author Harry Abraham
     */
    public double calculateTotalCost(double foodSubtotal, double deliveryFee, double tipAmount) {
        return foodSubtotal + deliveryFee + tipAmount;
    }
}