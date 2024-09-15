-- liquibase formatted sql

-- changeset yuriy-kolosov:25
ALTER TABLE reports ADD COLUMN is_sent BOOLEAN;