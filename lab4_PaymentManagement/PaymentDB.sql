
/**
 * Author:  Ethan
 * Created: Mar. 19, 2025
 * This SQL file contains the initalization of the DB on a new system
 */
CREATE DATABASE PaymentDB;

CREATE TABLE Wallet (
    wallet_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    balance DECIMAL(10,2) DEFAULT 0.00
);

CREATE TABLE Transaction (
    transaction_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    transaction_type ENUM('Deposit', 'Withdraw', 'Bet') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);