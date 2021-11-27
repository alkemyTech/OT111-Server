create table if not EXISTS members (
--  Fields
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL,
    facebook_url VARCHAR NULL,
    instagram_url VARCHAR NULL,
    linkedin_url VARCHAR NULL,
    image VARCHAR NOT NULL,
    description VARCHAR NULL,
--  Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,
--  SoftDelete
    deleted bool NOT NULL DEFAULT FALSE,

    CONSTRAINT members_pk PRIMARY KEY (id)
);
