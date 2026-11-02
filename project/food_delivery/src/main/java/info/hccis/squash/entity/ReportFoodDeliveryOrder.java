package info.hccis.squash.entity;

import info.hccis.squash.jpa.entity.FoodDeliveryOrder;
import java.util.ArrayList;

/**
 * Holds the input and the results for the Order Summary Report.
 * - orderId: what the user types on the report page
 * - orders: what the DAO found for that order id
 *
 * @author Harry Abraham
 * @since 20261101
 * Issue#5
 */
public class ReportFoodDeliveryOrder {

    private Integer orderId;
    private ArrayList<FoodDeliveryOrder> orders = new ArrayList<>();

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ArrayList<FoodDeliveryOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<FoodDeliveryOrder> orders) {
        this.orders = orders;
    }
}