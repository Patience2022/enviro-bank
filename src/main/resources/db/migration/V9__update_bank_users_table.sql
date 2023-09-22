

ALTER TABLE bank_users
    DROP COLUMN role;

ALTER TABLE bank_users
ADD COLUMN user_name VARCHAR(50) NOT NULL UNIQUE ;
