create table contact (
--  Fields
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    message VARCHAR(100) NOT NULL,
--  SoftDelete
    deleted bool NOT NULL DEFAULT FALSE,

    CONSTRAINT contact_pk PRIMARY KEY (id)
);