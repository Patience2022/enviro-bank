ALTER TABLE customers
    RENAME COLUMN customer_identity_number TO identity_number;


ALTER TABLE customers
    RENAME COLUMN customer_id TO id;

ALTER TABLE customers
    ADD COLUMN email varchar(50) NOT NULL UNIQUE ;
ALTER TABLE customers
    ADD COLUMN password varchar(80);

ALTER TABLE customers
    ADD COLUMN role varchar(30);

ALTER TABLE customers RENAME TO bank_users;

ALTER TABLE bank_users ADD  COLUMN locked BOOLEAN;
ALTER TABLE bank_users ADD  COLUMN enabled BOOLEAN;
