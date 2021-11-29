create table member (
--  Fields
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    facebook_url VARCHAR(500) NULL,
    instagram_url VARCHAR(500) NULL,
    linkedin_url VARCHAR(500) NULL,
    image VARCHAR(255) NOT NULL,
    description VARCHAR(255) NULL,
--  Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,
--  SoftDelete
    deleted bool NOT NULL DEFAULT FALSE,

    CONSTRAINT member_pk PRIMARY KEY (id)
);
