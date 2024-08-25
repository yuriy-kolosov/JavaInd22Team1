-- liquibase formatted sql

-- changeset mariiam:1
CREATE TABLE users (
	id bigserial PRIMARY KEY,
	f_name VARCHAR(255) NOT NULL,
	s_name VARCHAR(255) NOT NULL,
	l_name VARCHAR(255) NOT NULL,
	login VARCHAR(255) NOT NULL,
	phone INT8 NOT NULL,
	reg_date timestamp NOT NULL,
	"comment" VARCHAR(255) NOT NULL
);
CREATE TABLE volunteers (
	id bigserial PRIMARY KEY,
	f_name VARCHAR(255) NOT NULL,
	s_name VARCHAR(255) NOT NULL,
	l_name VARCHAR(255) NOT NULL,
	login VARCHAR(255) NOT NULL,
	phone INT8 NOT NULL,
	reg_date timestamp NOT NULL,
	"comment" VARCHAR(255) NOT NULL
);
CREATE TABLE animals (
	id bigserial PRIMARY KEY,
	"name" VARCHAR(255) NOT NULL,
	age INT8 NOT NULL,
	breed VARCHAR(255) NOT NULL,
	reg_date timestamp NOT NULL,
	"comment" VARCHAR(255) NOT NULL
);

CREATE TABLE recommendations (
	id bigserial PRIMARY KEY,
	title VARCHAR(255) NOT NULL
);
CREATE TABLE animal_recommendation (
	id bigserial PRIMARY KEY,
	animal_id INT8 NOT NULL REFERENCES animals (id) ON DELETE CASCADE,
	recommendation_id INT8 NOT NULL REFERENCES recommendations (id) ON DELETE CASCADE
);
CREATE TABLE features (
	id bigserial PRIMARY KEY,
	title VARCHAR(255) NOT NULL
);

CREATE TABLE animal_feature (
	id bigserial PRIMARY KEY,
	animal_id INT8 NOT NULL REFERENCES animals (id) ON DELETE CASCADE,
	feature_id INT8 NOT NULL REFERENCES features (id) ON DELETE CASCADE
);
CREATE TABLE statuses (
	id bigserial PRIMARY KEY,
	title VARCHAR(255) NOT NULL
);

CREATE TABLE requests (
	id bigserial PRIMARY KEY,
	user_id INT8 NOT NULL REFERENCES users (id) ON DELETE CASCADE,
	animal_id INT8 NOT NULL REFERENCES animals (id) ON DELETE CASCADE,
	reg_date timestamp NOT NULL,
	chat_id INT8 NOT NULL,
	"comment" VARCHAR(255) NOT NULL,
	status_id INT8 NOT NULL REFERENCES statuses (id)
);

CREATE TABLE reasons_rejection (
	id bigserial PRIMARY KEY,
	title VARCHAR(255) NOT NULL
);

CREATE TABLE rejections (
	id bigserial PRIMARY KEY,
	request_id INT8 NOT NULL REFERENCES requests (id) ON DELETE CASCADE,
	reason_rejection_id INT8 NOT NULL REFERENCES reasons_rejection (id) ON DELETE CASCADE,
	"date" timestamp NOT NULL,
	"comment" VARCHAR(255) NOT NULL
);

CREATE TABLE documents (
	id bigserial PRIMARY KEY,
	pasport oid NOT NULL,
	medical_certificate oid NOT NULL,
	salary_certificate oid NOT NULL
);

CREATE TABLE request_document (
	id bigserial PRIMARY KEY,
	request_id INT8 NOT NULL REFERENCES requests (id) ON DELETE CASCADE,
	document_id INT8 NOT NULL REFERENCES documents (id) ON DELETE CASCADE
);

CREATE TABLE adoptions (
	id bigserial PRIMARY KEY,
	request_id INT8 NOT NULL REFERENCES requests (id) ON DELETE CASCADE,
	volunteer_id INT8 NOT NULL REFERENCES volunteers (id) ON DELETE CASCADE,
	"date" timestamp NOT NULL
);