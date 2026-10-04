package cis;

public class FoodDeliveryOrder {

    private int orderId;
    private String customerName;
    private String restaurantName;
    private double foodSubtotal;
    private double deliveryDistance;
    private double tipAmount;

    public FoodDeliveryOrder() {
    }

    public FoodDeliveryOrder(int orderId, String customerName,
                             String restaurantName, double foodSubtotal,
                             double deliveryDistance, double tipAmount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.restaurantName = restaurantName;
        this.foodSubtotal = foodSubtotal;
        this.deliveryDistance = deliveryDistance;
        this.tipAmount = tipAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    public double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
    }
}