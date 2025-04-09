CREATE DATABASE IF NOT EXISTS CasinoSearchService;
USE CasinoSearchService;

-- Table: ACCOUNT
CREATE TABLE IF NOT EXISTS ACCOUNT (
    accountID VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    balance DOUBLE DEFAULT 0.0,
);

-- Table: BET
CREATE TABLE IF NOT EXISTS BET (
    betID INT AUTO_INCREMENT PRIMARY KEY,
    accountID VARCHAR(50),
    game VARCHAR(100),
    amount DOUBLE NOT NULL,
    outcome VARCHAR(20), -- e.g., "win", "loss", "pending"
    FOREIGN KEY (accountID) REFERENCES ACCOUNT(accountID)
);
