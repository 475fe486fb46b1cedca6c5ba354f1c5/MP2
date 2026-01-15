CREATE DATABASE IF NOT EXISTS db;
USE db;

CREATE TABLE IF NOT EXISTS user (id int NOT NULL AUTO_INCREMENT, user varchar(255) NOT NULL, pw varchar(255) NOT NULL, PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS threads (id int NOT NULL AUTO_INCREMENT, hd varchar(255),body varchar(2048), creator int NOT NULL, PRIMARY KEY(id), FOREIGN KEY(creator) REFERENCES user(id));

CREATE TABLE comments (id int NOT NULL AUTO_INCREMENT, text varchar(2048), commentor int NOT NULL, thread int NOT NULL, PRIMARY KEY(id), FOREIGN KEY(commentor) REFERENCES user(id), FOREIGN KEY(thread) REFERENCES threads(id));
