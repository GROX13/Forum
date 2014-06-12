DROP DATABASE IF EXISTS forum;

CREATE DATABASE forum;
USE forum;

CREATE TABLE users (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	avatar TEXT,
	username VARCHAR(25) UNIQUE,
	first_name VARCHAR(25),
	last_name VARCHAR(25),
	email VARCHAR(50),
	signiture TEXT,
	gender CHAR,
	birth_date DATE,
	registration_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	user_type INT,
	password VARCHAR(32)
);

CREATE TABLE message (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	sender_id INT NOT NULL,
	receiver_id INT NOT NULL,
	message TEXT,
	send_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (sender_id) REFERENCES users(id),
	FOREIGN KEY (receiver_id) REFERENCES users(id)
);

CREATE TABLE message_images (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	image_file TEXT,
	message_id INT NOT NULL,
	FOREIGN KEY (message_id) REFERENCES message(id)
);

CREATE TABLE message_videos (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	video_file TEXT,
	message_id INT NOT NULL,
	FOREIGN KEY (message_id) REFERENCES message(id)
);


CREATE TABLE categories (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(50) UNIQUE,
	description TEXT
);

CREATE TABLE theme (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	creator_id INT NOT NULL,
	category_id INT NOT NULL,
	title VARCHAR(50),
	description TEXT,
	creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	is_open BOOL,
	FOREIGN KEY (category_id) REFERENCES categories(id),
	FOREIGN KEY (creator_id) REFERENCES users(id)
);

CREATE TABLE posts (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	author_id INT NOT NULL,
	theme_id INT NOT NULL,
	post TEXT,
	add_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (author_id) REFERENCES users(id),
	FOREIGN KEY (theme_id) REFERENCES theme(id)
);

CREATE TABLE post_images (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	image_file TEXT,
	post_id INT NOT NULL,
	FOREIGN KEY (post_id) REFERENCES posts(id)
);

CREATE TABLE post_videos (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	video_file TEXT,
	post_id INT NOT NULL,
	FOREIGN KEY (post_id) REFERENCES posts(id)
);

CREATE TABLE warn (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL,
	last_post INT,
	start_date DATE,
	end_date DATE,
	frequency TIME,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (last_post) REFERENCES posts(id)
);

CREATE TABLE bann (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL,
	start_date DATE,
	end_date DATE,
	FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (username, first_name, last_name, email, signiture, gender, user_type) 
	VALUES ("GROX13", "Giorgi", "Rokhadze", "grokh12@freeuni.edu.ge", "G.Rokhadze", 'm', 1);

INSERT INTO users (username, first_name, last_name, email, signiture, gender, user_type) 
	VALUES ("Giorgi", "Giorgi", "Rokhadze", "grokh12@freeuni.edu.ge", "G.Rokhadze", 'm', 0);

INSERT INTO message (sender_id, receiver_id, message) 
	VALUES (1, 2, 'Test message 1');

INSERT INTO message (sender_id, receiver_id, message) 
	VALUES (2, 1, 'Test message 2');

INSERT INTO message (sender_id, receiver_id, message) 
	VALUES (2, 1, 'Test message 3');

SELECT * FROM users;
SELECT * FROM categories;
SELECT * FROM message;
SELECT * FROM message_images;
SELECT * FROM message_videos;