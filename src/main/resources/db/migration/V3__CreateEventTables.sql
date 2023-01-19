
CREATE TABLE fight
(
    id int NOT NULL AUTO_INCREMENT,
    challenger_id int NOT NULL,
    challenged_id int NOT NULL,
    rounds int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(challenger_id) REFERENCES fighter(id) ON DELETE CASCADE,
    FOREIGN KEY(challenged_id) REFERENCES fighter(id) ON DELETE CASCADE
);



CREATE TABLE country
(
    id int NOT NULL AUTO_INCREMENT,
    country_name varchar(50) NOT NULL,
    country_code varchar(2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE city
(
    id int NOT NULL AUTO_INCREMENT,
    city_name varchar(100) NOT NULL UNIQUE ,
    country_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(country_id) REFERENCES country(id)
);



CREATE TABLE event
(
    id   int     NOT NULL AUTO_INCREMENT,
    event_name varchar(100) NOT NULL,
    city_id int NOT NULL,
    sold_tickets int DEFAULT 0,
    available_tickets int NOT NULL,
    ticket_price double precision NOT NULL,
    date_time DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(city_id) REFERENCES city(id)
);
CREATE TABLE fight_card
(
    id int NOT NULL AUTO_INCREMENT,
    order_nr int NOT NULL,
    event_id int NOT NULL,
    fight_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(fight_id) REFERENCES fight(id) ON DELETE CASCADE,
    FOREIGN KEY(event_id) REFERENCES event(id) ON DELETE CASCADE
);
CREATE TABLE ticket
(
    id int NOT NULL AUTO_INCREMENT,
    event_id int NOT NULL,
    user_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(event_id) REFERENCES event(id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);
