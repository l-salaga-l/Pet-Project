--liquibase formatted sql
--changeset Damir Nurlygayanov:EX-1 localFilePath:01.000.00/report.sql


CREATE TABLE report
(
    id      UUID NOT NULL,
    title   VARCHAR(255),
    creation_date    date NOT NULL,
    content VARCHAR(255),
    last_modified_date  date NOT NULL,
    CONSTRAINT pk_report PRIMARY KEY (id)
);