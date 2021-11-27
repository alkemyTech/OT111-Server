-- CREATE user TABLE

CREATE TABLE users (

    id_user int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    photo VARCHAR NOT NULL,
    role_id int8,
    deleted bool NOT NULL DEFAULT false,
    created_date timestamptz(0) NOT NULL DEFAULT now(),
    created_by varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,

    CONSTRAINT user_pk PRIMARY KEY (id_user),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_fk FOREIGN KEY (role_id) REFERENCES public.roles(id_role)
);