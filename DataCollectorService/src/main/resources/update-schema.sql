DROP TABLE notification;

CREATE TABLE notification
(
    id           UUID NOT NULL,
    created_data TIMESTAMP WITHOUT TIME ZONE,
    header       VARCHAR(255),
    body         VARCHAR(255),
    CONSTRAINT pk_notification PRIMARY KEY (id)
);