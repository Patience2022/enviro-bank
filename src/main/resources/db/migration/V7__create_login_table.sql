CREATE TABLE IF NOT EXISTS login
(
    id SERIAL PRIMARY KEY NOT NULL ,
    token  VARCHAR NOT NULL ,
    created_at TIMESTAMP DEFAULT now() NOT NULL ,
    expire_at TIMESTAMP,
    confirmed_at TIMESTAMP,
    bank_user SERIAL NOT NULL ,
    CONSTRAINT c_id FOREIGN KEY(bank_user) REFERENCES bank_users( id)  ON UPDATE CASCADE
);