-- liquibase formatted sql

-- changeset mariiam:4
ALTER TABLE users ADD COLUMN chat_id integer;
ALTER TABLE users ADD COLUMN type integer;