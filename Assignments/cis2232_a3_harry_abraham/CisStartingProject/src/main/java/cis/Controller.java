package cis;

import cis.bo.FoodDeliveryOrderBO;
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
 * Multi-threaded Food Delivery application (Assignment 3).
 *
 * The program runs in two separate threads:
 * - The main thread runs the menu in the console.
 * - A second thread runs the same menu using JOptionPane.
 *
 * Both threads share the same list of orders, the same JSON file and
 * the same static accumulator variables (total orders and total value).
 * The shared methods are synchronized so that only one thread can change
 * the shared data at a time.
 *
 * @author Harry George Abraham
 * @since 2026-10-16
 */
public class Controller {

    private static final String DIRECTORY = "C:\\cis2232";
    private static final String FILE_NAME = "data_abraham_harry.json";

    private static final Path DIRECTORY_PATH = Paths.get(DIRECTORY);
    private static final Path FILE_PATH = Paths.get(DIRECTORY, FILE_NAME);

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Shared by both threads.
    private static ArrayList<FoodDeliveryOrder> orders = new ArrayList<>();

    // Static accumulator variables shared by both threads.
    private static int totalNumberOfOrders = 0;
    private static double totalValueOfOrders = 0;

    public static void main(String[] args) {

        // Create the directory if needed and load previously saved orders.
        createDirectory();
        loadOrders();

        // Second thread: the menu using JOptionPane.
        FoodDeliveryMenu guiMenu = new FoodDeliveryMenu(true);
        Thread guiThread = new Thread(guiMenu, "JOptionPane Thread");
        guiThread.start();

        // Main thread: the menu using the console.
        Thread.currentThread().setName("Console Thread");
        FoodDeliveryMenu consoleMenu = new FoodDeliveryMenu(false);
        consoleMenu.run();

        // Wait for the JOptionPane thread to finish before closing.
        try {
            guiThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("Both threads have finished. Goodbye.");
        System.exit(0);
    }

    /**
     * Adds an order to the shared list, updates the accumulator variables
     * and saves all orders to the JSON file.
     *
     * Synchronized so the two threads cannot add at the same moment.
     *
     * @param order the order to add
     * @return a message showing the order total and the combined totals
     */
    public static synchronized String addOrder(FoodDeliveryOrder order) {

        orders.add(order);

        double orderTotal = FoodDeliveryOrderBO.calculate(order);

        // Update the accumulator variables.
        totalNumberOfOrders++;
        totalValueOfOrders += orderTotal;

        saveOrders();

        return "Order added successfully by the "
                + Thread.currentThread().getName()
                + "\nOrder Total Cost: $" + String.format("%.2f", orderTotal)
                + "\n\n" + getAccumulatorText();
    }

    /**
     * Builds the text showing all orders from both threads.
     *
     * Synchronized so the list is not changed while it is being read.
     *
     * @return the orders and the combined totals as text
     */
    public static synchronized String getOrdersReport() {

        if (orders.isEmpty()) {
            return "No orders have been added.";
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
                    .append(String.format("%.2f", order.getFoodSubtotal()))
                    .append("\nDelivery Distance: ")
                    .append(order.getDeliveryDistance())
                    .append(" km")
                    .append("\nTip Amount: $")
                    .append(String.format("%.2f", order.getTipAmount()))
                    .append("\nTotal Cost: $")
                    .append(String.format("%.2f",
                            FoodDeliveryOrderBO.calculate(order)))
                    .append("\n-------------------------\n");
        }

        output.append("\n").append(getAccumulatorText());

        return output.toString();
    }

    /**
     * Builds the text showing the combined totals from both threads.
     *
     * @return the accumulator information as text
     */
    private static String getAccumulatorText() {
        return "Combined Totals (both threads)"
                + "\nTotal Number of Orders: " + totalNumberOfOrders
                + "\nTotal Value of Orders: $"
                + String.format("%.2f", totalValueOfOrders);
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
            System.out.println("Error creating directory: " + e.getMessage());
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
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    /**
     * Loads previously saved food delivery orders from the JSON file
     * and sets the accumulator variables from those orders.
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

            // Start the accumulator from the orders already in the file.
            for (FoodDeliveryOrder order : orders) {
                totalNumberOfOrders++;
                totalValueOfOrders += FoodDeliveryOrderBO.calculate(order);
            }

        } catch (IOException e) {
            System.out.println("Error loading orders: " + e.getMessage());
        }
    }
}