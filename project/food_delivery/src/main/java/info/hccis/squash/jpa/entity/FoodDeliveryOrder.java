package info.hccis.squash.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class to hold the details of one food delivery order.
 * One object of this class = one row in the FoodDeliveryOrder table.
 *
 * @author Harry Abraham
 * @since 20261101
 * Issue#5
 */
@Entity
@Table(name = "FoodDeliveryOrder")
public class FoodDeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "restaurantName")
    private String restaurantName;

    @Column(name = "foodSubtotal")
    private double foodSubtotal;

    @Column(name = "deliveryDistance")
    private double deliveryDistance;

    @Column(name = "deliveryFee")
    private double deliveryFee;

    @Column(name = "tipAmount")
    private double tipAmount;

    @Column(name = "totalCost")
    private double totalCost;

    public FoodDeliveryOrder() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getFoodSubtotal() {
        return foodSubtotal;
    }

    public void setFoodSubtotal(double foodSubtotal) {
        this.foodSubtotal = foodSubtotal;
    }

    public double getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * One readable line for this order. Used when writing the report file.
     */
    @Override
    public String toString() {
        return String.format("Order ID: %d | Customer: %s | Restaurant: %s | Food Subtotal: $%.2f"
                        + " | Distance: %.2f km | Delivery Fee: $%.2f | Tip: $%.2f | Total Cost: $%.2f",
                orderId, customerName, restaurantName, foodSubtotal,
                deliveryDistance, deliveryFee, tipAmount, totalCost);
    }
}