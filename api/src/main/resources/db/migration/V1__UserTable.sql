CREATE TABLE IF NOT EXISTS `User` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `mail_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `authority` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE goods_mst (
  goods_id varchar(10) NOT NULL,
  goods_name varchar(256) NOT NULL,
  price decimal(9,0) NOT NULL,
  PRIMARY KEY (goods_id)
);

CREATE DATABASE sbdb;

CREATE TABLE counter1 (
id SERIAL PRIMARY KEY
, title VARCHAR(16)
, count INT);

CREATE TABLE customer_mst (
  customer_id varchar(10) NOT NULL,
  customer_name varchar(256) NOT NULL,
  age tinyint(3) unsigned NOT NULL,
  gender tinyint(1) unsigned NOT NULL,
  address varchar(256) DEFAULT NULL,
  PRIMARY KEY (customer_id)
);
CREATE TABLE sales_trn (
  sales_id varchar(10) NOT NULL,
  sales_detail_no int(11) NOT NULL,
  customer_id varchar(10) NOT NULL,
  goods_id varchar(10) NOT NULL,
  quantity int(11) DEFAULT NULL,
  amount decimal(9,2) DEFAULT NULL,
  sales_date datetime DEFAULT NULL,
  PRIMARY KEY (sales_id, sales_detail_no)
);
