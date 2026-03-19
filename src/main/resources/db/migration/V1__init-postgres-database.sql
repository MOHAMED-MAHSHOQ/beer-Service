DROP TABLE IF EXISTS beer;
DROP TABLE IF EXISTS customer;

CREATE TABLE beer (
                      id               VARCHAR(36)   NOT NULL,
                      beer_name        VARCHAR(50)   NOT NULL,
                      beer_style       SMALLINT  NOT NULL,
                      created_date     TIMESTAMP,
                      price            DECIMAL(38,2) NOT NULL,
                      quantity_on_hand INTEGER,
                      upc              VARCHAR(255)  NOT NULL,
                      update_date      TIMESTAMP,
                      version          INTEGER,
                      PRIMARY KEY (id)
);

CREATE TABLE customer (
                          id           VARCHAR(36) NOT NULL,
                          created_date TIMESTAMP,
                          name         VARCHAR(255),
                          update_date  TIMESTAMP,
                          version      INTEGER,
                          PRIMARY KEY (id)
);