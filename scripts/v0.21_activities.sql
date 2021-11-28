CREATE TABLE  activities (

    id INTEGER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL,
    content TEXT NOT NULL,
    image VARCHAR NULL,

--  Timestamps

    created_date timestamp(0) NOT NULL DEFAULT NOW(),
    created_by varchar(100) NOT NULL,
    modified_date timestamp(0) NULL,
    modified_by varchar(100) NULL,

--  Soft Delete
    deleted bool NOT NULL DEFAULT FALSE

);