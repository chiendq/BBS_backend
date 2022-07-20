USE bbs;
INSERT INTO bbs.account (username, email, password) VALUES ('test12345', 'test12345@gmail.com', 'test12345');
INSERT INTO bbs.account (username, email, password) VALUES ('demo54321', 'demo54321@gmail.com', 'demo54321');

INSERT INTO bbs.post (title, content, author_name, created_at, updated_on, thumbnail, account_id) VALUES ('tittle1', 'content1', 'author1', '2022-07-19 13:28:02', '2022-07-19 13:28:07', 'thumbnail1', 1);
INSERT INTO bbs.post (title, content, author_name, created_at, updated_on, thumbnail, account_id) VALUES ('tittle2', 'content2', 'author2', '2022-07-19 13:28:08', '2022-07-19 13:28:09', 'thumbnail2', 2);
INSERT INTO bbs.post (title, content, author_name, created_at, updated_on, thumbnail, account_id) VALUES ('title3', 'content3', 'author3', '2022-07-19 13:28:13', '2022-07-19 13:28:10', 'thumbnail3', 1);
