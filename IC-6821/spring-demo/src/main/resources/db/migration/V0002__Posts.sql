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

