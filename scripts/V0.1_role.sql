-- CREATE roles TABLE

CREATE TABLE roles
(

    id            int2         NOT NULL,
    "name"        VARCHAR(100) NOT NULL,
    description   VARCHAR(255) NULL,
    created_date  timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by    varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by   varchar(100) NULL,
    deleted       bool         NOT NULL DEFAULT FALSE,

    CONSTRAINT role_pk PRIMARY KEY (id)
);
