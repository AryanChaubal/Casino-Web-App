-- Create the database
CREATE DATABASE IF NOT EXISTS casino_game;
USE casino_game;

-- Create the User table
CREATE TABLE IF NOT EXISTS User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00
);