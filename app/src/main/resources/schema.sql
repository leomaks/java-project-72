DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at Timestamp
);

DROP TABLE IF EXISTS url_checks;

CREATE TABLE url_checks (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status_code INT,
    title VARCHAR(255),
    h1 TEXT,
    description TEXT,
    url_id bigint REFERENCES urls(id) ON DELETE CASCADE,
    created_at Timestamp
);




