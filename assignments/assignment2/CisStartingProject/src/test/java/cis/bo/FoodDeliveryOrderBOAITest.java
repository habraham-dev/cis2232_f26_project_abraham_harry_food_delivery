package cis.bo;

import cis.FoodDeliveryOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * AI-generated unit test suite for FoodDeliveryOrderBO.
 *
 * The AI was provided with the Food Delivery Calculator
 * project requirements and the FoodDeliveryOrder entity class.
 *
 * Project calculation requirements:
 *
 * Delivery Fee = Delivery Distance * $1.50
 *
 * Total Cost = Food Subtotal + Delivery Fee + Tip Amount
 *
 * These tests were generated to test the calculate method
 * using a variety of food delivery order scenarios.
 *
 * @author Harry George Abraham
 */
public class FoodDeliveryOrderBOAITest {

    /**
     * Tests an order with a decimal delivery distance.
     *
     * Subtotal = $20.00
     * Distance = 2.5 km
     * Delivery Fee = $3.75
     * Tip = $2.00
     *
     * Expected Total = $25.75
     */
    @Test
    public void testCalculateDecimalDistance() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                101,
                "Customer One",
                "Restaurant One",
                20.00,
                2.50,
                2.00
        );

        double result = FoodDeliveryOrderBO.calculate(order);

        assertEquals(25.75, result, 0.001);
    }

    /**
     * Tests an order with no tip.
     *
     * Subtotal = $30.00
     * Distance = 5 km
     * Delivery Fee = $7.50
     * Tip = $0.00
     *
     * Expected Total = $37.50
     */
    @Test
    public void testCalculateWithoutTip() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                102,
                "Customer Two",
                "Restaurant Two",
                30.00,
                5.00,
                0.00
        );

        double result = FoodDeliveryOrderBO.calculate(order);

        assertEquals(37.50, result, 0.001);
    }

    /**
     * Tests an order with zero delivery distance.
     *
     * Subtotal = $45.00
     * Distance = 0 km
     * Delivery Fee = $0.00
     * Tip = $5.00
     *
     * Expected Total = $50.00
     */
    @Test
    public void testCalculateZeroDeliveryDistance() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                103,
                "Customer Three",
                "Restaurant Three",
                45.00,
                0.00,
                5.00
        );

        double result = FoodDeliveryOrderBO.calculate(order);

        assertEquals(50.00, result, 0.001);
    }

    /**
     * Tests a larger order.
     *
     * Subtotal = $100.00
     * Distance = 20 km
     * Delivery Fee = $30.00
     * Tip = $10.00
     *
     * Expected Total = $140.00
     */
    @Test
    public void testCalculateLargeOrder() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                104,
                "Customer Four",
                "Restaurant Four",
                100.00,
                20.00,
                10.00
        );

        double result = FoodDeliveryOrderBO.calculate(order);

        assertTrue(Math.abs(result - 140.00) < 0.001);
    }

    /**
     * Tests an order containing decimal monetary values.
     *
     * Subtotal = $19.99
     * Distance = 3 km
     * Delivery Fee = $4.50
     * Tip = $2.50
     *
     * Expected Total = $26.99
     */
    @Test
    public void testCalculateDecimalValues() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                105,
                "Customer Five",
                "Restaurant Five",
                19.99,
                3.00,
                2.50
        );

        double result = FoodDeliveryOrderBO.calculate(order);

        assertEquals(26.99, result, 0.001);
    }
}