CREATE TABLE posts (
    id INT IDENTITY PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    body LONGVARCHAR NOT NULL,
    user_id INT,
    ext_id VARCHAR(36) UNIQUE NOT NULL,
    created_on TIMESTAMP DEFAULT NOW,
    last_updated_on TIMESTAMP DEFAULT NOW,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TRIGGER posts_set_audit_fields
BEFORE INSERT ON posts
REFERENCING NEW ROW AS newrow FOR EACH ROW
BEGIN ATOMIC
    SET newrow.ext_id = UUID();
    SET newrow.created_on = NOW();
    SET newrow.last_updated_on = NOW();
END;