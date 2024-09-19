-- liquibase formatted sql

-- changeset mariiam:7
ALTER TABLE users ALTER COLUMN "comment" DROP NOT NULL;
ALTER TABLE users ALTER COLUMN l_name DROP NOT NULL;
ALTER TABLE users ALTER COLUMN chat_id SET NOT NULL;
ALTER TABLE users ALTER COLUMN type SET NOT NULL;