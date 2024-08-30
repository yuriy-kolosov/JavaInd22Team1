-- liquibase formatted sql

-- changeset mariiam:5
ALTER TABLE  users  ALTER COLUMN "type" TYPE VARCHAR(255);