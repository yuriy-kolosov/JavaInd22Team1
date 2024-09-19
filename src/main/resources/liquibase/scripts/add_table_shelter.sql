-- liquibase formatted sql

-- changeset distrog:2

create table shelters (
	id                      bigserial       PRIMARY KEY,    -- автоинкрементируемый идентификатор приюта
	name                    VARCHAR(255)    NOT NULL,       -- наименование приюта
	type                    VARCHAR(255)    NOT NULL,       -- тип приюта
	contacts                VARCHAR(255)    NOT NULL,       -- контактная информация приюта
	media_type              VARCHAR(255)    NOT NULL,       -- тип (расширение) файла со схемой проезда к приюту (.jpeg)
	rules                   VARCHAR(255)    NOT NULL,       -- правила работы приюта
	location_scheme         bytea           NOT NULL        -- схема проезда к приюту (файл .jpeg)
);
