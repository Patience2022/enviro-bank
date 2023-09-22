CREATE TABLE IF NOT EXISTS customers (
    customer_id SERIAL PRIMARY KEY,
    customer_identity_number VARCHAR(13) UNIQUE NOT NULl,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone_number VARCHAR(50) NOT NULl UNIQUE
    );
