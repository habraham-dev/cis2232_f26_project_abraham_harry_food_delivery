package info.hccis.squash.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity class to hold the details of one food delivery order.
 * One object of this class = one row in the FoodDeliveryOrder table.
 *
 * The validation annotations (@NotNull, @Size) are checked by Spring when the
 * add/edit form is submitted (see @Valid in FoodDeliveryOrderController).
 *
 * @author Harry Abraham
 * @since 20261101
 * Issue#5
 * @modified 20261123 Harry Abraham Issue#6 Added Spring validation annotations
 * and changed the user-entered amounts to Double so @NotNull can catch empty
 * values.
 */
@Entity
@Table(name = "FoodDeliveryOrder")
public class FoodDeliveryOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderId")
    private Integer orderId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "customerName")
    private String customerName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "restaurantName")
    private String restaurantName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "foodSubtotal")
    private Double foodSubtotal;

    @Basic(optional = false)
    @NotNull
    @Column(name = "deliveryDistance")
    private Double deliveryDistance;

    //Calculated by FoodDeliveryOrderBO (user does not enter this).
    @Column(name = "deliveryFee")
    private double deliveryFee;

    @Basic(optional = false)
    @NotNull
    @Column(name = "tipAmount")
    private Double tipAmount;

    //Calculated by FoodDeliveryOrderBO (user does not enter this).
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

    public Double getFoodSubtotal() {
        return foodSubtotal;
    }

    public void setFoodSubtotal(Double foodSubtotal) {
        this.foodSubtotal = foodSubtotal;
    }

    public Double getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(Double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Double tipAmount) {
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