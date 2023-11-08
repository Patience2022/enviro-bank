CREATE TABLE IF NOT EXISTS password_reset_tokens
(
    id SERIAL PRIMARY KEY NOT NULL ,
    token  VARCHAR NOT NULL ,
    created_at TIMESTAMP DEFAULT now() NOT NULL ,
    expire_at TIMESTAMP,
    confirmed_at TIMESTAMP,
    user_id SERIAL NOT NULL ,
    CONSTRAINT c_id FOREIGN KEY(user_id) REFERENCES bank_users( id)  ON UPDATE CASCADE
);