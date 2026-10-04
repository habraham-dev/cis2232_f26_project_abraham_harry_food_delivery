package cis.bo;

import cis.FoodDeliveryOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for FoodDeliveryOrderBO.
 *
 * These tests were created following a Test Driven Development approach.
 * The calculation requirements were considered first, tests were created,
 * and the business logic was implemented and verified using the tests.
 *
 * @author Harry George Abraham
 */
public class FoodDeliveryOrderBOTest {

    /**
     * TDD Test 1 - Standard food delivery order.
     *
     * Food subtotal = $25.00
     * Delivery distance = 4 km
     * Delivery fee = 4 * $1.50 = $6.00
     * Tip = $3.00
     *
     * Expected total = $34.00
     */
    @Test
    public void testCalculateStandardOrder() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                1,
                "Harry",
                "Test Restaurant",
                25.00,
                4.00,
                3.00
        );

        double actualTotal = FoodDeliveryOrderBO.calculate(order);

        assertEquals(34.00, actualTotal, 0.001);
    }

    /**
     * TDD Test 2 - Order with no delivery distance and no tip.
     *
     * Food subtotal = $10.00
     * Delivery distance = 0 km
     * Delivery fee = $0.00
     * Tip = $0.00
     *
     * Expected total = $10.00
     */
    @Test
    public void testCalculateZeroDistanceAndTip() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                2,
                "John",
                "Pizza Place",
                10.00,
                0.00,
                0.00
        );

        double actualTotal = FoodDeliveryOrderBO.calculate(order);

        assertEquals(10.00, actualTotal, 0.001);
    }

    /**
     * TDD Test 3 - Larger delivery order.
     *
     * Food subtotal = $50.00
     * Delivery distance = 10 km
     * Delivery fee = 10 * $1.50 = $15.00
     * Tip = $5.00
     *
     * Expected total = $70.00
     */
    @Test
    public void testCalculateLargeOrder() {

        FoodDeliveryOrder order = new FoodDeliveryOrder(
                3,
                "Sarah",
                "Burger House",
                50.00,
                10.00,
                5.00
        );

        double actualTotal = FoodDeliveryOrderBO.calculate(order);

        assertTrue(Math.abs(actualTotal - 70.00) < 0.001);
    }
}