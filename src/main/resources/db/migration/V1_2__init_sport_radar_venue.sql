create table venue
(
    id              VARCHAR(32) NOT NULL,
    name            VARCHAR(32) NOT NULL,
    capacity        BIGINT NOT NULL,
    city_name       VARCHAR(32) NOT NULL,
    country_name    VARCHAR(32) NOT NULL,
    map_coordinates VARCHAR(32) NOT NULL,
    country_code    VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);