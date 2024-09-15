-- liquibase formatted sql

-- changeset distrog:22
ALTER TABLE reports DROP COLUMN request_id;
ALTER TABLE reports DROP COLUMN addiction;
ALTER TABLE reports DROP COLUMN comment;
ALTER TABLE reports RENAME COLUMN health TO general;
ALTER TABLE reports RENAME COLUMN behavior_changes TO behavior;
ALTER TABLE reports ADD COLUMN is_accepted BOOLEAN;
ALTER TABLE reports ADD COLUMN media_type VARCHAR(255);