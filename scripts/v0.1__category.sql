create table category (
--  Fields
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(255) NULL,
    image       VARCHAR(255) NULL,
-- Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by  varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,
--  SoftDelete
    deleted     bool NOT NULL DEFAULT FALSE,
    CONSTRAINT  category_pk PRIMARY KEY (id)
);