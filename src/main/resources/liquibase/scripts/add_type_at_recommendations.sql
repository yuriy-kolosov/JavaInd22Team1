-- liquibase formatted sql

-- changeset yuriy-kolosov:13
ALTER TABLE recommendations ADD COLUMN type varchar(255);