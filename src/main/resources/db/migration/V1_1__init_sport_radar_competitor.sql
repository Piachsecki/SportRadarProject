create table competitor
(
    id           VARCHAR(32) NOT NULL,
    name         VARCHAR(32) NOT NULL,
    country      VARCHAR(32) NOT NULL,
    country_code VARCHAR(32) NOT NULL,
    abbreviation VARCHAR(32) NOT NULL,
    qualifier    VARCHAR(32) NOT NULL,
    gender       VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);