DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    bigint GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    created_at timestamp
);

