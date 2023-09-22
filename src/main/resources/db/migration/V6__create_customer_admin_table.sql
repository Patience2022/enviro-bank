CREATE TABLE IF NOT EXISTS customers
(
    id INTEGER,
    CONSTRAINT c_fk FOREIGN KEY (id) REFERENCES bank_users(id)

);
CREATE TABLE IF NOT EXISTS admin
(
    id INTEGER,
        CONSTRAINT a_fk FOREIGN KEY (id) REFERENCES bank_users(id)
)





