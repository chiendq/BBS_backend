-- Users schema

-- !Ups

CREATE TABLE `post` (
                        `id` CHAR(36) CHARACTER SET ascii PRIMARY KEY,
                        `title` text NOT NULL,
                        `content` text NOT NULL,
                        `author_name` text NOT NULL,
                        `created_at` datetime NOT NULL,
                        `updated_on` datetime NOT NULL,
                        `thumbnail` text NOT NULL ,
                        `account_id` CHAR(36) CHARACTER SET ascii NOT NULL
);

CREATE TABLE `account` (
                           `id` CHAR(36) CHARACTER SET ascii PRIMARY KEY ,
                           `username` text NOT NULL,
                           `email` text NOT NULL,
                           `password` text NOT NULL
);

ALTER TABLE `post` ADD FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);


-- !Downs

DROP TABLE user;