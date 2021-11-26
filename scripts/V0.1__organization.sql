CREATE TABLE  organization (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL,
    image VARCHAR NOT NULL,
    address VARCHAR NULL,
    phone INT NULL,
    email VARCHAR NOT NULL,
    welcomeText VARCHAR NOT NULL,
    aboutUsText VARCHAR NULL,
--    Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,
--    Soft Delete
    deleted bool NOT NULL DEFAULT FALSE
);