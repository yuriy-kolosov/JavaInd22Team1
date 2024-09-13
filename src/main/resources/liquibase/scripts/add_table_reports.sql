-- liquibase formatted sql

-- changeset mariiam:12
CREATE TABLE reports (
	id bigserial PRIMARY KEY,
	request_id INT8 NOT NULL,
	animal_photo OID,
	diet VARCHAR(255),
	health VARCHAR(255),
	addiction VARCHAR(255),
	behavior_changes VARCHAR(255) ,
	report_date timestamp NOT NULL,
	"comment" VARCHAR(255)
);