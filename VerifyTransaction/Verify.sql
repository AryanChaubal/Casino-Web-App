CREATE DATABASE IF NOT EXISTS VerifyTransactionService;
USE VerifyTransactionService;

-- Table: ACCOUNT (duplicate in both services for microservice separation)
CREATE TABLE IF NOT EXISTS ACCOUNT (
    accountID VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    balance DOUBLE DEFAULT 0.0
);

-- Table: Verify
CREATE TABLE IF NOT EXISTS Verify (
    transactionID INT,
    accountID VARCHAR(50),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (accountID) REFERENCES ACCOUNT(accountID)
);
