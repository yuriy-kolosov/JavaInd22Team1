-- liquibase formatted sql

-- changeset mariiam:3
ALTER TABLE  users  ALTER COLUMN phone TYPE VARCHAR(255);