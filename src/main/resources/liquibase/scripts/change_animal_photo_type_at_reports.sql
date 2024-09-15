-- liquibase formatted sql

-- changeset distrog:23
ALTER TABLE reports DROP COLUMN animal_photo;
ALTER TABLE reports ADD COLUMN animal_photo bytea;