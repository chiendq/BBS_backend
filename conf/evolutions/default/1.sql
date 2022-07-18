-- Users schema

-- !Ups

CREATE TABLE `post` (
                        `id` bigint PRIMARY KEY AUTO_INCREMENT,
                        `title` nvarchar(150) NOT NULL,
                        `content` text NOT NULL,
                        `author_name` nvarchar(50) NOT NULL,
                        `created_at` datetime NOT NULL,
                        `updated_on` datetime NOT NULL,
                        `thumbnail` text NOT NULL ,
                        `account_id` bigint NOT NULL
);

CREATE TABLE `account` (
                           `id` bigint PRIMARY KEY AUTO_INCREMENT,
                           `username` varchar(50) NOT NULL,
                           `email` varchar(255) NOT NULL,
                           `password` varchar(255) NOT NULL
);

ALTER TABLE `post` ADD FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);


-- !Downs

DROP TABLE user;