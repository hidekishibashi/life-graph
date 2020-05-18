INSERT INTO `User` (
  `name`,
  `password`,
  `mail_address`
) VALUES
  ('taro', '12345', 'seattle'),
  ('Hanako', 'pass', 'academy'),
  ('Yamada', '12345', 'mountain'),
  ('Taro', 'Gogogo', 'desk');

--   CREATE TABLE `Parent_Chart` (
--   `id` bigint NOT NULL AUTO_INCREMENT,
--   `user_id` bigint NOT NULL,
--   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `user_id` (`user_id`),
--   CONSTRAINT `Parent_Chart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- CREATE TABLE `Child_Chart` (
--   `id` bigint NOT NULL AUTO_INCREMENT,
--   `parent_id` bigint NOT NULL,
--   `score` int NOT NULL,
--   `age` int NOT NULL,
--   `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `parent_id` (`parent_id`,`age`),
--   UNIQUE KEY `UKqyrxsc9ri7kkiv4kckekopnny` (`parent_id`),
--   UNIQUE KEY `UKm7wu6d4f1wmp2phfchl22gv6h` (`age`),
--   CONSTRAINT `child_chart_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `Parent_Chart` (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;