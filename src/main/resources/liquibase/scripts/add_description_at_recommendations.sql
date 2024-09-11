-- liquibase formatted sql

-- changeset mariiam:11
ALTER TABLE recommendations ADD COLUMN description varchar(255);