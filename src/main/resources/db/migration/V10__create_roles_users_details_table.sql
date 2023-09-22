CREATE TABLE IF NOT EXISTS user_roles_details
(
    user_id SERIAL NOT NULL ,
    role_id SERIAL NOT NULL ,

    CONSTRAINT sr_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES bank_users(id),
    CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES user_roles(id)
);