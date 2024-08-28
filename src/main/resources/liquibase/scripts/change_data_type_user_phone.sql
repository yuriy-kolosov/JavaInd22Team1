-- liquibase formatted sql

-- changeset mariiam:3
--changeset liquibase-docs:modifyDataType-example
ALTER  TABLE  users.phone  MODIFY  id  VARCHAR(255);