CREATE TABLE orders (
    id   INTEGER     NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    description VARCHAR NOT NULL,
    amount decimal NOT NULL,
    PRIMARY KEY (id)
);