create table user_role(
	usersid int8 ,
	rolesid int8 ,
    CONSTRAINT usersrolesid PRIMARY KEY (usersid, rolesid),
    CONSTRAINT usersid_roles_id_fk FOREIGN KEY (usersid)
      REFERENCES users (id),
    CONSTRAINT rolesid_usersid_fk FOREIGN KEY (rolesid)
      REFERENCES roles (id)
)