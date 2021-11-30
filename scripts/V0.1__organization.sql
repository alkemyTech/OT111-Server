CREATE TABLE  organization (
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(500) NOT NULL,
    address VARCHAR(255) NULL,
    phone INT NULL,
    email VARCHAR(255) NOT NULL,
    welcomeText TEXT NOT NULL,
    aboutUsText TEXT NULL,
--    Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by VARCHAR(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by VARCHAR(100) NULL,
--    Soft Delete
    deleted bool NOT NULL DEFAULT FALSE,

    CONSTRAINT organization_pk PRIMARY KEY (id)
);