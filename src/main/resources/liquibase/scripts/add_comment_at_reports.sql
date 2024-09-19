-- liquibase formatted sql

-- changeset yuriy-kolosov:24
ALTER TABLE reports ADD COLUMN comment varchar(255);