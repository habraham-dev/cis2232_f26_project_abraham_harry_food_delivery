package cis;

import cis.util.CisUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Console based Food Delivery application.
 *
 * Allows the user to add and view food delivery orders.
 * Order information is saved to a JSON file so that the
 * information is still available after the program closes.
 *
 * @author Harry George Abraham
 */
public class Controller {

    private static final CisUtility cisUtility = new CisUtility();

    private static final String DIRECTORY = "C:\\cis2232";
    private static final String FILE_NAME = "data_abraham_harry.json";

    private static final Path DIRECTORY_PATH = Paths.get(DIRECTORY);
    private static final Path FILE_PATH = Paths.get(DIRECTORY, FILE_NAME);

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static ArrayList<FoodDeliveryOrder> orders = new ArrayList<>();

    public static final String EXIT = "X";

    private static final String MENU
            = "\n-------------------------\n"
            + "Food Delivery Calculator\n"
            + "A) Add\n"
            + "V) View\n"
            + "X) Exit\n"
            + "-------------------------\n"
            + "Option-->";

    public static void main(String[] args) {

        // Assignment requires a console based application.
        cisUtility.setIsGUI(false);

        // Create the directory if needed and load previously saved orders.
        createDirectory();
        loadOrders();

        String option;

        do {
            option = cisUtility.getInputString(MENU, "Green");
            processMenuOption(option);
        } while (!option.equalsIgnoreCase(EXIT));
    }

    /**
     * Processes the option selected from the main menu.
     *
     * @param option menu option entered by the user
     */
    public static void processMenuOption(String option) {

        switch (option.toUpperCase()) {

            case "A":
                addOrder();
                break;

            case "V":
                viewOrders();
                break;

            case "X":
                cisUtility.display("Exiting Food Delivery Calculator");
                break;

            default:
                cisUtility.display("Invalid entry");
                break;
        }
    }

    /**
     * Gets the food delivery order information from the user,
     * adds the order to the list, and saves the data to the JSON file.
     */
    private static void addOrder() {

        FoodDeliveryOrder order = new FoodDeliveryOrder();

        int orderId = cisUtility.getInputInt("Enter Order ID:");
        String customerName
                = cisUtility.getInputString("Enter Customer Name:");
        String restaurantName
                = cisUtility.getInputString("Enter Restaurant Name:");
        double foodSubtotal
                = cisUtility.getInputDouble("Enter Food Subtotal:");
        double deliveryDistance
                = cisUtility.getInputDouble("Enter Delivery Distance:");
        double tipAmount
                = cisUtility.getInputDouble("Enter Tip Amount:");

        order.setOrderId(orderId);
        order.setCustomerName(customerName);
        order.setRestaurantName(restaurantName);
        order.setFoodSubtotal(foodSubtotal);
        order.setDeliveryDistance(deliveryDistance);
        order.setTipAmount(tipAmount);

        orders.add(order);

        saveOrders();

        cisUtility.display("Order added successfully");
    }

    /**
     * Displays all food delivery orders that have been created.
     */
    public static void viewOrders() {

        if (orders.isEmpty()) {
            cisUtility.display("No orders have been added.");
            return;
        }

        StringBuilder output = new StringBuilder();

        output.append("Food Delivery Orders\n");

        for (FoodDeliveryOrder order : orders) {

            output.append("\n-------------------------")
                    .append("\nOrder ID: ")
                    .append(order.getOrderId())
                    .append("\nCustomer Name: ")
                    .append(order.getCustomerName())
                    .append("\nRestaurant Name: ")
                    .append(order.getRestaurantName())
                    .append("\nFood Subtotal: $")
                    .append(order.getFoodSubtotal())
                    .append("\nDelivery Distance: ")
                    .append(order.getDeliveryDistance())
                    .append(" km")
                    .append("\nTip Amount: $")
                    .append(order.getTipAmount())
                    .append("\n-------------------------\n");
        }

        cisUtility.display(output.toString());
    }

    /**
     * Creates the C:\cis2232 directory if it does not already exist.
     */
    private static void createDirectory() {

        try {

            if (!Files.exists(DIRECTORY_PATH)) {
                Files.createDirectories(DIRECTORY_PATH);
            }

        } catch (IOException e) {
            cisUtility.display(
                    "Error creating directory: " + e.getMessage());
        }
    }

    /**
     * Saves all food delivery orders to the required JSON file.
     */
    private static void saveOrders() {

        try {

            String json = gson.toJson(orders);

            Files.write(FILE_PATH, json.getBytes());

        } catch (IOException e) {
            cisUtility.display(
                    "Error saving orders: " + e.getMessage());
        }
    }

    /**
     * Loads previously saved food delivery orders from the JSON file.
     */
    private static void loadOrders() {

        if (!Files.exists(FILE_PATH)) {
            return;
        }

        try {

            String json = new String(Files.readAllBytes(FILE_PATH));

            if (json.trim().isEmpty()) {
                return;
            }

            Type orderListType
                    = new TypeToken<ArrayList<FoodDeliveryOrder>>() {
            }.getType();

            ArrayList<FoodDeliveryOrder> savedOrders
                    = gson.fromJson(json, orderListType);

            if (savedOrders != null) {
                orders = savedOrders;
            }

        } catch (IOException e) {
            cisUtility.display(
                    "Error loading orders: " + e.getMessage());
        }
    }
}