CREATE DATABASE db_order;
USE db_order;
CREATE TABLE t_order0 (
                          id BIGINT,
                          order_no VARCHAR(30),
                          user_id BIGINT,
                          PRIMARY KEY(id)
);
CREATE TABLE t_order1 (
                          id BIGINT,
                          order_no VARCHAR(30),
                          user_id BIGINT,
                          PRIMARY KEY(id)
);
CREATE TABLE t_order_item0(
                              id BIGINT,
                              user_id BIGINT,
                              order_id BIGINT,
                              price DECIMAL(10,2),
                              `count` INT,
                              PRIMARY KEY(id)
);

CREATE TABLE t_order_item1(
                              id BIGINT,
                              user_id BIGINT,
                              order_id BIGINT,
                              price DECIMAL(10,2),
                              `count` INT,
                              PRIMARY KEY(id)
);