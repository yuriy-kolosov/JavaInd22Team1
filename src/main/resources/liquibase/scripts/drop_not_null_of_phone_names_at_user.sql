-- liquibase formatted sql

-- changeset mariiam:8
ALTER TABLE users
    ALTER COLUMN phone DROP NOT NULL,
    ALTER COLUMN s_name DROP NOT NULL,
    ALTER COLUMN f_name DROP NOT NULL;