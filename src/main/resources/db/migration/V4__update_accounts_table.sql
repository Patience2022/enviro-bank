ALTER TABLE accounts
   DROP COLUMN customer_number;

ALTER TABLE accounts
    ADD COLUMN customer SERIAL NOT NULL;

ALTER TABLE     accounts
    ADD CONSTRAINT client FOREIGN KEY(customer) REFERENCES customers(customer_id)  ON UPDATE CASCADE;

