
CREATE SCHEMA IF NOT EXISTS identity;

CREATE TABLE IF NOT EXISTS identity.users (
    user_id BIGINT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email_address VARCHAR(255) UNIQUE,
    contact_number VARCHAR(255) UNIQUE,
    is_active BOOLEAN DEFAULT true,
    user_password VARCHAR(255),
    date_of_birth DATE,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP,
    CONSTRAINT email_unique UNIQUE (email_address),
    CONSTRAINT contact_number_unique UNIQUE (contact_number)
);

CREATE TABLE IF NOT EXISTS identity.roles (
    role_id BIGINT PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE,
    role_description VARCHAR(255),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS identity.user_roles (
    user_id BIGINT REFERENCES identity.users(user_id),
    role_id BIGINT REFERENCES identity.roles(role_id),
    PRIMARY KEY (user_id, role_id)
);