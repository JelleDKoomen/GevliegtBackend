-- Switch to your database
\c gevliegtDatabase;

--to create the databse
--CREATE DATABASE gevliegtDatabase;
--Do this in the terminal to create a user
--CREATE USER admin WITH ENCRYPTED PASSWORD 'password';
--GRANT ALL PRIVILEGES ON DATABASE gevliegtDatabase TO admin; 

-- User Table
CREATE TABLE users (
    userID SERIAL PRIMARY KEY,
    userName VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    passwordHash TEXT NOT NULL,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    birthDate DATE,
    role VARCHAR(20) CHECK (role IN ('User', 'Admin', 'SuperAdmin')),
    profilePicture TEXT
);

-- WishList Table
CREATE TABLE wishlists (
    wishlistID SERIAL PRIMARY KEY,
    userID INT NOT NULL REFERENCES users(userID) ON DELETE CASCADE
);

-- Wishlist Items Table (Normalized)
CREATE TABLE wishlist_items (
    wishlistItemID SERIAL PRIMARY KEY,
    wishlistID INT NOT NULL REFERENCES wishlists(wishlistID) ON DELETE CASCADE,
    itemName VARCHAR(255) NOT NULL
    link TEXT,
    price DECIMAL(10,2) DEFAULT 0.00 CHECK (price >= 0)
);

-- Agenda-Item Table
CREATE TABLE agenda_items (
    agendaItemID SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    eventDateTime TIMESTAMP NOT NULL,
    location VARCHAR(100)
);

-- Recipes Table
CREATE TABLE recipes (
    recipeID SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    veggie BOOLEAN DEFAULT FALSE,
    description TEXT,
    userID INT NOT NULL REFERENCES users(userID) ON DELETE CASCADE,
    steps TEXT NOT NULL,
    duration INT CHECK (duration > 0)
);

-- Merch Item Table
CREATE TABLE merch_items (
    merchItemID SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    productImage TEXT,
    price DECIMAL(10,2) DEFAULT 0.00 CHECK (price >= 0)
);

-- Gallery Item Table
CREATE TABLE gallery_items (
    itemID SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    galleryImage TEXT,
    userPosted INT NOT NULL REFERENCES users(userID) ON DELETE CASCADE,
    datePosted TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Library Item Table
CREATE TABLE library_items (
    bookID SERIAL PRIMARY KEY,
    userID INT NOT NULL REFERENCES users(userID) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    genre VARCHAR(100)
);
