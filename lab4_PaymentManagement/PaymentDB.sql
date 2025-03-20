
/**
 * Author:  Ethan
 * Created: Mar. 19, 2025
 * This SQL file contains the initalization of the DB on a new system
 */
-- Create the database
CREATE DATABASE IF NOT EXISTS casino_payment;
USE casino_payment;

-- Create the Wallet table
CREATE TABLE IF NOT EXISTS Wallet (
    wallet_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00
);

-- Create the TRANSACTION table
CREATE TABLE IF NOT EXISTS TRANSACTION (
    transactionID VARCHAR(50) PRIMARY KEY,
    wallet_id INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transactionDate VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id)
);