SELECT * FROM user;

CREATE TABLE customer(
                         customer_id VARCHAR(35) PRIMARY KEY ,
                         name VARCHAR(155) NOT NULL,
                         address TEXT NOT NULL,
                         tel VARCHAR(15) NOT NULL
);

SHOW TABLES;

DESC customer;