-- liquibase formatted sql

-- changeset mariiam:11
ALTER TABLE shelters ADD COLUMN schedule varchar(255);
ALTER TABLE shelters ADD COLUMN address varchar(255);
ALTER TABLE shelters ADD COLUMN directions oid;
ALTER TABLE shelters ADD COLUMN safety_recommendations varchar(255);