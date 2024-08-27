-- liquibase formatted sql

-- changeset distrog:2

create table shelters (
	id bigserial PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	type VARCHAR(255) NOT NULL,
	contacts VARCHAR(255) NOT NULL,
	media_type VARCHAR(255) NOT NULL,
	rules VARCHAR(255) NOT NULL,
	location_schema bytea NOT NULL
);