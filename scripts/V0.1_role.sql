-- CREATE roles TABLE

CREATE TABLE roles (

    id_role int8 NOT NULL GENERATED AlWAYS AS IDENTITY,
    "name" VARCHAR NOT NULL,
    description VARCHAR NULLABLE,
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,
    deleted bool NOT NULL DEFAULT FALSE,

    CONSTRAINT role_pk PRIMARY KEY (id_role)
);