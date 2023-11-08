CREATE TABLE IF NOT EXISTS transactions
(
    id SERIAL NOT NULL PRIMARY KEY ,
    amount NUMERIC(10,3) NOT NULL ,
    date TIMESTAMP NOT NULL DEFAULT date(now()),
    description VARCHAR(50) NOT NULL,
    reference VARCHAR(50) NOT NULL,
    service_fee NUMERIC(10,3),
    type SERIAL NOT NULL,
    account SERIAL NOT NULL,
    pending BOOLEAN default false,
    destination VARCHAR(50) NOT NULL ,
    CONSTRAINT type_fk FOREIGN KEY (type) REFERENCES transaction_types(id),
    CONSTRAINT acc_fk FOREIGN KEY (account) REFERENCES accounts(account_id)

);