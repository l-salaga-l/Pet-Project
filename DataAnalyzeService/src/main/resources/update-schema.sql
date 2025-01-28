DROP table report;

CREATE TABLE report
(
    id      UUID NOT NULL,
    title   VARCHAR(255),
    date    date NOT NULL,
    content VARCHAR(255),
    update  date NOT NULL,
    CONSTRAINT pk_report PRIMARY KEY (id)
);