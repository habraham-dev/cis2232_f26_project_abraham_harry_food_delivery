# Food Delivery Calculator

CIS2232 Project

## Description

The Food Delivery Calculator is a web application designed to calculate the total cost of a food delivery order. The application will allow users to enter information about a food order, such as the customer name, restaurant name, food subtotal, delivery distance, and tip amount.

The application will calculate the delivery fee based on the delivery distance and then calculate the final cost of the order. The order information and calculated values will be stored in a database so that orders can be added, viewed, updated, and deleted.

## Color

Dark Red

## Required Fields

| Field | Type | Description |
|---|---|---|
| Order ID | Integer | A unique identifier automatically assigned to each order. |
| Customer Name | String | The name of the customer placing the order. |
| Restaurant Name | String | The name of the restaurant. |
| Food Subtotal | Double | The total price of the food before delivery fee and tip. |
| Delivery Distance | Double | The delivery distance in kilometers. |
| Delivery Fee | Double | The delivery charge calculated using the delivery distance. |
| Tip Amount | Double | The tip added by the customer. |
| Total Cost | Double | The final calculated cost of the order. |

## Calculation

The application will calculate the delivery fee based on the delivery distance.

Delivery Fee = Delivery Distance × $1.50

The final order cost will then be calculated as:

Total Cost = Food Subtotal + Delivery Fee + Tip Amount

For example, if the food subtotal is $25.00, the delivery distance is 4 km, and the tip is $3.00:

Delivery Fee = 4 × $1.50 = $6.00

Total Cost = $25.00 + $6.00 + $3.00 = $34.00

## Report Details

The application will generate an Order Summary Report.

The user will enter an Order ID to generate the report.

The report will display:
- Order ID
- Customer Name
- Restaurant Name
- Food Subtotal
- Delivery Distance
- Delivery Fee
- Tip Amount
- Total Cost

The report will also be written to a file.