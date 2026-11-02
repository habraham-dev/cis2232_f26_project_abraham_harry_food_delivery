package info.hccis.squash.dao;

import info.hccis.squash.jpa.entity.FoodDeliveryOrder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO class to access food delivery orders using JDBC.
 *
 * @author Harry Abraham
 * @since 20261101
 * Issue#5
 */
public class FoodDeliveryOrderDAO {

    private Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(FoodDeliveryOrderDAO.class);

    /**
     * Open a connection to the database using the settings in application.properties.
     */
    public FoodDeliveryOrderDAO() {

        ResourceBundle rb = ResourceBundle.getBundle("application");
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error("Could not connect to the database: " + e.getMessage());
        }
    }

    /**
     * Select the food delivery order with the given order id.
     *
     * @param orderId the order id entered by the user
     * @return list of matching orders (empty if none found)
     * @since 20261101
     * @author Harry Abraham
     */
    public ArrayList<FoodDeliveryOrder> selectFoodDeliveryOrders(int orderId) {

        ArrayList<FoodDeliveryOrder> orders = new ArrayList<>();

        //If the connection failed, return the empty list.
        if (conn == null) {
            logger.error("No database connection. Returning an empty list.");
            return orders;
        }

        String query = "SELECT * FROM FoodDeliveryOrder WHERE orderId = ?";

        try {
            //Prepare the query and put the order id into the ? placeholder.
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderId);

            //Run the query.
            ResultSet rs = stmt.executeQuery();

            //Copy each row from the database into a FoodDeliveryOrder object.
            while (rs.next()) {
                FoodDeliveryOrder order = new FoodDeliveryOrder();
                order.setOrderId(rs.getInt("orderId"));
                order.setCustomerName(rs.getString("customerName"));
                order.setRestaurantName(rs.getString("restaurantName"));
                order.setFoodSubtotal(rs.getDouble("foodSubtotal"));
                order.setDeliveryDistance(rs.getDouble("deliveryDistance"));
                order.setDeliveryFee(rs.getDouble("deliveryFee"));
                order.setTipAmount(rs.getDouble("tipAmount"));
                order.setTotalCost(rs.getDouble("totalCost"));
                orders.add(order);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            logger.error("Error running the report query: " + e.getMessage());
        } finally {
            //Always close the connection when finished.
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("Could not close the connection: " + e.getMessage());
            }
        }

        logger.info("Found orders: " + orders.size());
        return orders;
    }
}
