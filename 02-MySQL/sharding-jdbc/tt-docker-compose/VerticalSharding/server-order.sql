CREATE DATABASE db_order;
USE db_order;
CREATE TABLE t_order (
                         id BIGINT AUTO_INCREMENT,
                         order_no VARCHAR(30),
                         user_id BIGINT,
                         PRIMARY KEY(id)
);