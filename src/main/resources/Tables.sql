--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables

--Seller--
DROP TABLE Seller IF EXISTS;
CREATE TABLE Seller (
    seller_id int primary key,
    name varchar(255) NOT NULL
);
INSERT INTO Seller (seller_id, name)
VALUES
--(1, 'Marc'),
--(2, 'Ted');

--Product--
DROP TABLE Product IF EXISTS;
CREATE TABLE Product (
    product_id int primary key,
    brand varchar(255) NOT NULL,
    model varchar(255) NOT NULL,
    price double NOT NULL,
    name varchar(255) NOT NULL
);
INSERT INTO Product (product_id, brand, model, price, name)
VALUES
--(1, 'Microsoft', 'Xbox', 249.99, 'Marc'),
--(2, 'Sony', 'PS2', 199.99, 'Ted');