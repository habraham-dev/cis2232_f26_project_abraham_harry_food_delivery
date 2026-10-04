DROP DATABASE IF EXISTS cis2232_food_delivery;
CREATE DATABASE cis2232_food_delivery;
USE cis2232_food_delivery;

CREATE TABLE FoodDeliveryOrder
(
    orderId            int(5),
    customerName       varchar(100) NOT NULL COMMENT 'Customer name',
    restaurantName     varchar(100) NOT NULL COMMENT 'Restaurant name',
    foodSubtotal       decimal(10,2) NOT NULL COMMENT 'Food subtotal',
    deliveryDistance   decimal(10,2) NOT NULL COMMENT 'Delivery distance in kilometres',
    deliveryFee        decimal(10,2) NOT NULL COMMENT 'Calculated delivery fee',
    tipAmount          decimal(10,2) NOT NULL COMMENT 'Tip amount',
    totalCost          decimal(10,2) NOT NULL COMMENT 'Calculated total cost'
) COMMENT 'This table holds food delivery order details';

ALTER TABLE FoodDeliveryOrder
    ADD PRIMARY KEY (orderId);

ALTER TABLE FoodDeliveryOrder
    MODIFY orderId int(5) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;