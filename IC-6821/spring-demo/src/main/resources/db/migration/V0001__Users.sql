CREATE TABLE users (
    id INT IDENTITY PRIMARY KEY,
    username VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(256) NOT NULL,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    ext_id VARCHAR(36) UNIQUE NOT NULL,
    created_on TIMESTAMP DEFAULT NOW,
    last_updated_on TIMESTAMP DEFAULT NOW
);

CREATE TRIGGER users_set_audit_fields
BEFORE INSERT ON users
REFERENCING NEW ROW AS newrow FOR EACH ROW
BEGIN ATOMIC
    SET newrow.ext_id = UUID();
    SET newrow.created_on = NOW();
    SET newrow.last_updated_on = NOW();
END;
