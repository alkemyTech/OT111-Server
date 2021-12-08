create table news (
--  Fields
    id      int8            NOT NULL GENERATED ALWAYS AS IDENTITY,
    "name"    VARCHAR(100)    NOT NULL,
    content TEXT            NOT NULL,
    image   VARCHAR(100) NULL,
    category_id int8 NOT NULL,
--  Timestamps
    created_date timestamptz(0) NOT NULL DEFAULT NOW(),
    created_by varchar(100) NULL,
    modified_date timestamptz(0) NULL,
    modified_by varchar(100) NULL,
--  SoftDelete
    deleted bool NOT NULL DEFAULT FALSE,

    CONSTRAINT news_pk PRIMARY KEY (id),
    CONSTRAINT news_fk FOREIGN KEY (category_id) REFERENCES category(id)

);

