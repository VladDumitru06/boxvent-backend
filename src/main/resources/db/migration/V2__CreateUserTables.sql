
CREATE TABLE user
(
    id   int     NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE user_role
(
    id int NOT NULL AUTO_INCREMENT,
    role_name varchar(50) NOT NULL,
    user_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);

