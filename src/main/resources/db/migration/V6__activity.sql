CREATE TABLE  activity (
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image VARCHAR(255) NULL,
--  Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by VARCHAR(100) NOT NULL,
    modified_date timestamptz(0) NULL,
    modified_by VARCHAR(100) NULL,
--  Soft Delete
    deleted bool NOT NULL DEFAULT false,

    CONSTRAINT activity_pk PRIMARY KEY (id)
	);
