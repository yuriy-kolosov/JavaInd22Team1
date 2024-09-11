-- liquibase formatted sql

-- changeset distrog:13
ALTER TABLE shelters ALTER COLUMN location_scheme DROP NOT NULL;
