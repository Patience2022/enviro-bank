
ALTER TABLE accounts
ALTER COLUMN customer_number TYPE VARCHAR(13);

ALTER TABLE accounts
    ADD CONSTRAINT client FOREIGN KEY(customer_number) REFERENCES customers( customer_identity_number)  ON UPDATE CASCADE;

