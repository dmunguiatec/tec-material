CREATE TABLE users (
    id              SERIAL PRIMARY KEY,
    username        VARCHAR(256),
    name            VARCHAR(256),
    created_on      TIMESTAMP DEFAULT NOW(),
    last_updated_on TIMESTAMP DEFAULT NOW()
)