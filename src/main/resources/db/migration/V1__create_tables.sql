CREATE TABLE IF NOT EXISTS account_types (
    id SERIAL PRIMARY KEY,
    account_type_name VARCHAR(50) NOT NULl UNIQUE,
    maximum_overdraft_limit NUMERIC(10,3) NOT NULl DEFAULT 0,
    minimum_amount_required NUMERIC(10,3) NOT NULl DEFAULT 0
);
CREATE TABLE IF NOT EXISTS accounts (
    account_id SERIAL PRIMARY KEY,
    customer_number VARCHAR(50) NOT NULl,
    account_number VARCHAR(50) NOT NULl UNIQUE,
    balance NUMERIC(10,3) NOT NULl,
    creation_date Timestamp NOT NULL DEFAULT now(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    overdraft NUMERIC(10,3) NOT NULl DEFAULT 0,
    amount_available NUMERIC(10,3) DEFAULT 0,
    closure_date Timestamp,
    account_type SERIAL NOT NULL,
    CONSTRAINT ct_fk FOREIGN KEY(account_type ) REFERENCES account_types(id)  ON UPDATE CASCADE
);



