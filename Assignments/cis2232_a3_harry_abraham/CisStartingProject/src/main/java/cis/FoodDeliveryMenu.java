package cis;

import cis.util.CisUtility;

/**
 * Runs the Food Delivery menu (Add / View / Exit).
 *
 * This class implements Runnable so that it can be run in its own thread.
 * One object is created for the console and one for JOptionPane. Each
 * object has its own CisUtility so the two threads work independently.
 *
 * @author Harry George Abraham
 * @since 2026-10-16
 */
public class FoodDeliveryMenu implements Runnable {

    public static final String EXIT = "X";

    private static final String MENU
            = "\n-------------------------\n"
            + "Food Delivery Calculator\n"
            + "A) Add\n"
            + "V) View\n"
            + "X) Exit\n"
            + "-------------------------\n"
            + "Option-->";

    private final CisUtility cisUtility = new CisUtility();
    private final boolean isGUI;

    /**
     * Creates a menu for either JOptionPane or the console.
     *
     * @param isGUI true for JOptionPane, false for the console
     */
    public FoodDeliveryMenu(boolean isGUI) {
        this.isGUI = isGUI;
        cisUtility.setIsGUI(isGUI);
    }

    /**
     * Shows the menu until the user chooses X.
     * This is the code that runs inside the thread.
     */
    @Override
    public void run() {

        String option;

        do {
            if (isGUI) {
                option = cisUtility.getInputString(MENU);
            } else {
                option = cisUtility.getInputString(MENU, "Green");
            }

            // Pressing Cancel or closing the window returns null.
            if (option == null) {
                option = EXIT;
            }

            processMenuOption(option.trim());

        } while (!option.trim().equalsIgnoreCase(EXIT));
    }

    /**
     * Processes the option selected from the menu.
     *
     * @param option menu option entered by the user
     */
    private void processMenuOption(String option) {

        switch (option.toUpperCase()) {

            case "A":
                addOrder();
                break;

            case "V":
                cisUtility.display(Controller.getOrdersReport());
                break;

            case "X":
                cisUtility.display("Exiting Food Delivery Calculator ("
                        + Thread.currentThread().getName() + ")");
                break;

            default:
                cisUtility.display("Invalid entry");
                break;
        }
    }

    /**
     * Gets the order information from the user and passes the new order
     * to the Controller, which adds it to the shared list and file.
     */
    private void addOrder() {

        try {

            FoodDeliveryOrder order = new FoodDeliveryOrder();

            order.setOrderId(cisUtility.getInputInt("Enter Order ID:"));
            order.setCustomerName(
                    cisUtility.getInputString("Enter Customer Name:"));
            order.setRestaurantName(
                    cisUtility.getInputString("Enter Restaurant Name:"));
            order.setFoodSubtotal(
                    cisUtility.getInputDouble("Enter Food Subtotal:"));
            order.setDeliveryDistance(
                    cisUtility.getInputDouble("Enter Delivery Distance:"));
            order.setTipAmount(
                    cisUtility.getInputDouble("Enter Tip Amount:"));

            String message = Controller.addOrder(order);

            cisUtility.display(message);

        } catch (NumberFormatException | NullPointerException e) {
            cisUtility.display("Invalid number entered. Order not added.");
        }
    }
}