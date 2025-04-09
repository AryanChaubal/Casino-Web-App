CREATE DATABASE IF NOT EXISTS CasinoTransactionService;
USE CasinoTransactionService;

-- Table: ACCOUNT (duplicate in both services for microservice separation)
CREATE TABLE IF NOT EXISTS ACCOUNT (
    accountID VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    balance DOUBLE DEFAULT 0.0,
);

-- Table: TRANSACTION
CREATE TABLE IF NOT EXISTS TRANSACTION_LOG (
    transactionID INT AUTO_INCREMENT PRIMARY KEY,
    accountID VARCHAR(50),
    amount DOUBLE NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (accountID) REFERENCES ACCOUNT(accountID)
);
