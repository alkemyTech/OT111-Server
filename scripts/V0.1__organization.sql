CREATE TABLE  organization (
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL,
    image TEXT NOT NULL,
    address TEXT NULL,
    phone INT NULL,
    email TEXT NOT NULL,
    welcomeText TEXT NOT NULL,
    aboutUsText TEXT NULL,
--    Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by TEXT(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by TEXT(100) NULL,
--    Soft Delete
    deleted bool NOT NULL DEFAULT FALSE,

    CONSTRAINT organization_pk PRIMARY KEY (id)
);