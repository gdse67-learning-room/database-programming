DROP DATABASE IF EXISTS kade;

CREATE DATABASE IF NOT EXISTS kade;

USE kade;

CREATE TABLE user(
                     user_name VARCHAR(35) PRIMARY KEY,
                     name VARCHAR(155) NOT NULL,
                     password VARCHAR(155) NOT NULL,
                     tel VARCHAR(15) NOT NULL
);

DESC user;