CREATE TABLE fighter
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    profile varchar(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE boxing_record
(
    id int NOT NULL AUTO_INCREMENT,
    fighterid int(11) NOT NULL,
    wins int(10) NOT NULL,
    draws int(10) NOT NULL,
    loses int(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(fighterid) REFERENCES fighter(id)
);
