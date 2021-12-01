-- CREATE user TABLE

CREATE TABLE users (

    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    photo VARCHAR(500) NOT NULL,
    role_id int8,
    deleted bool NOT NULL DEFAULT false,
    created_date timestamptz(0) NOT NULL DEFAULT now(),
    created_by varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,

    CONSTRAINT user_pk PRIMARY KEY (id_user),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_fk FOREIGN KEY (role_id) REFERENCES roles(id)
);