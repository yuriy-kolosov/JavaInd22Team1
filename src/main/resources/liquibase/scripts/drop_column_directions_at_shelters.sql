-- liquibase formatted sql

-- changeset distrog:12

ALTER TABLE shelters
DROP COLUMN IF EXISTS directions;