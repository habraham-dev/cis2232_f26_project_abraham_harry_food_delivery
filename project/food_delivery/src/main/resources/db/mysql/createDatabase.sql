-- =====================================================================
-- CIS2232 Food Delivery Calculator - database setup
-- Run this whole script in phpMyAdmin (SQL tab).
-- WARNING: it deletes the old cis2232_food_delivery database first.
-- =====================================================================

DROP DATABASE IF EXISTS cis2232_food_delivery;
CREATE DATABASE cis2232_food_delivery;
USE cis2232_food_delivery;

-- ---------------------------------------------------------------------
-- Main table: one row = one food delivery order
-- ---------------------------------------------------------------------
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

-- Test orders so the report has something to show.
-- deliveryFee = deliveryDistance x 1.50
-- totalCost   = foodSubtotal + deliveryFee + tipAmount
INSERT INTO FoodDeliveryOrder
(customerName, restaurantName, foodSubtotal, deliveryDistance, deliveryFee, tipAmount, totalCost)
VALUES
    ('Sarah Murphy',   'Island Pizza', 25.00,  4.00,  6.00, 3.00, 34.00),
    ('John MacDonald', 'Burger Barn',  18.75,  2.00,  3.00, 2.00, 23.75),
    ('Emily Chen',     'Taco Town',    31.20, 10.00, 15.00, 4.50, 50.70),
    ('Liam Gallant',   'Sushi Garden', 42.50,  6.50,  9.75, 5.00, 57.25);

-- ---------------------------------------------------------------------
-- Code tables (used by the home page to pick an organization;
-- the top menu only appears after an organization is picked)
-- ---------------------------------------------------------------------
CREATE TABLE CodeType
(
    id                 int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    codeTypeId         int(3) COMMENT 'This is id for code types',
    englishDescription varchar(100) NOT NULL COMMENT 'English description',
    frenchDescription  varchar(100) DEFAULT NULL COMMENT 'French description',
    createdDateTime    datetime     DEFAULT NULL,
    createdUserId      varchar(20)  DEFAULT NULL,
    updatedDateTime    datetime     DEFAULT NULL,
    updatedUserId      varchar(20)  DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

INSERT INTO CodeType (codeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, ''),
       (2, 'Status Codes', 'Status Codes FR', sysdate(), '', CURRENT_TIMESTAMP, ''),
       (3, 'Organization Code', 'Organization Code FR', sysdate(), '', CURRENT_TIMESTAMP, '');

CREATE TABLE CodeValue
(
    id                      int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    codeTypeId              int(3)       NOT NULL COMMENT 'see code_type table',
    codeValueSequence       int(3)       NOT NULL COMMENT 'code value identifier',
    englishDescription      varchar(100) NOT NULL COMMENT 'English description',
    englishDescriptionShort varchar(20)  NOT NULL COMMENT 'English abbreviation for description',
    frenchDescription       varchar(100) DEFAULT NULL COMMENT 'French description',
    frenchDescriptionShort  varchar(20)  DEFAULT NULL COMMENT 'French abbreviation for description',
    sortOrder               int(3)       DEFAULT NULL COMMENT 'Sort order if applicable',
    createdDateTime         datetime     DEFAULT NULL,
    createdUserId           varchar(20)  DEFAULT NULL,
    updatedDateTime         datetime     DEFAULT NULL,
    updatedUserId           varchar(20)  DEFAULT NULL
) COMMENT ='This will hold code values for the application.';

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
       (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
       (2, 1, 'Active', 'Active', 'A', 'A', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
       (2, 2, 'Inactive', 'Inactive', 'I', 'I', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
       (3, 1, 'Food Delivery Calculator', 'Food Delivery', 'FD', 'FD', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');