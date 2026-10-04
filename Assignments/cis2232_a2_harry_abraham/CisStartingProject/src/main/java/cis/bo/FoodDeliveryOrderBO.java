package cis.bo;

import cis.FoodDeliveryOrder;

/**
 * Business object class for Food Delivery Order calculations.
 *
 * The calculation was implemented using a Test Driven Development
 * approach. A unit test was created first and initially failed.
 * The calculation logic was then added to make the test pass.
 *
 * @author Harry George Abraham
 */
public class FoodDeliveryOrderBO {

    private static final double DELIVERY_RATE_PER_KM = 1.50;

    /**
     * Calculates the total cost of a food delivery order.
     *
     * Delivery Fee = Delivery Distance * $1.50
     *
     * Total Cost = Food Subtotal + Delivery Fee + Tip Amount
     *
     * @param order the FoodDeliveryOrder entity
     * @return the calculated total cost
     */
    public static double calculate(FoodDeliveryOrder order) {

        double deliveryFee =
                order.getDeliveryDistance() * DELIVERY_RATE_PER_KM;

        double totalCost =
                order.getFoodSubtotal()
                        + deliveryFee
                        + order.getTipAmount();

        return totalCost;
    }
}