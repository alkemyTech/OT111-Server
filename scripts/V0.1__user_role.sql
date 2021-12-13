create table user_role
(
    user_id int8,
    role_id int8,
    CONSTRAINT user_role_id PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_fk_users FOREIGN KEY (user_id)
        REFERENCES users (id),
    CONSTRAINT user_role_fk_roles FOREIGN KEY (role_id)
        REFERENCES roles (id)
)