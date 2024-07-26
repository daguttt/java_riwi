CREATE DATABASE IF NOT EXISTS jdbc_workshop;

USE jdbc_workshop;

CREATE TABLE IF NOT EXISTS authors(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    nationality VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS books(
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    publishedDate DATE NOT NULL,
    price FLOAT NOT NULL,
    authors_id INT NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (authors_id) REFERENCES authors (id)
);
