CREATE TABLE testimonial (
   id INT8 NOT NULL GENERATED ALWAYS AS IDENTITY ,
   name     VARCHAR(200)  NOT NULL,
   image    VARCHAR(200)  NULL,
   content  VARCHAR(200)  NULL,

   --  Timestamps
       created_date timestamptz(0) NOT NULL DEFAULT NOW(),
       created_by varchar(100) NULL,
       modified_date timestamptz(0) NULL,
       modified_by varchar(100) NULL,
   --  SoftDelete
       deleted bool NOT NULL DEFAULT FALSE,

   CONSTRAINT testimonial_id PRIMARY KEY (id)
);
