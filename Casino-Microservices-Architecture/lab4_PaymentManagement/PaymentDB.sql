-- Create the database
CREATE DATABASE IF NOT EXISTS wallet_service;
USE wallet_service;

-- Create the Wallet table
CREATE TABLE IF NOT EXISTS Wallet (
    wallet_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00
);

-- Create the Transaction table
CREATE TABLE IF NOT EXISTS Transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    wallet_id INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date VARCHAR(20) NOT NULL,
    FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id)
);

-- Add indexes for faster lookups
CREATE INDEX idx_wallet_id ON Transaction(wallet_id);
CREATE INDEX idx_transaction_date ON Transaction(transaction_date);