CREATE TABLE slides.slides (
id INT GENERATED BY DEFAULT AS IDENTITY,
image_Url VARCHAR(255) NOT NULL,
organization_id INT NOT NULL,
text_value TEXT NULL,
order_value INT NULL);

create table slides.organization ;

ALTER TABLE slides.slides ADD CONSTRAINT slides_fk FOREIGN KEY (organization_id) REFERENCES slides.slides(slide_id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE TABLE slides.audit (
created_date timestamptz(0) NOT NULL DEFAULT NOW(),
created_by varchar(100) NULL,
modified_date timestamptz(0) NULL,
modified_by varchar(100) NULL,
deleted bool NOT NULL DEFAULT FALSE);


