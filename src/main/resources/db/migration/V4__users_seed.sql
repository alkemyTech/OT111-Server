--CREATE ROLES
INSERT INTO roles
    (id, "name", description)
VALUES (1, 'ROLE_USER', 'user role'),
       (2, 'ROLE_ADMIN', 'admin role');

--CREATE 20 USERS/ADMINS

INSERT INTO users
    (first_name, last_name, email, password, photo)

VALUES ('admin1', 'admin1', 'admin1@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin1.jpg'),
       ('admin2', 'admin2', 'admin2@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin2.jpg'),
       ('admin3', 'admin3', 'admin3@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin3.jpg'),
       ('admin4', 'admin4', 'admin4@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin4.jpg'),
       ('admin5', 'admin5', 'admin5@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin5.jpg'),
       ('admin6', 'admin6', 'admin6@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin6.jpg'),
       ('admin7', 'admin7', 'admin7@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin7.jpg'),
       ('admin8', 'admin8', 'admin8@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin8.jpg'),
       ('admin9', 'admin9', 'admin9@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin9.jpg'),
       ('admin10', 'admin10', 'admin10@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin10.jpg'),

       ('user1', 'user1', 'user1@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user1.jpg'),
       ('user2', 'user2', 'user2@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user2.jpg'),
       ('user3', 'user3', 'user3@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user3.jpg'),
       ('user4', 'user4', 'user4@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user4.jpg'),
       ('user5', 'user5', 'user5@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user5.jpg'),
       ('user6', 'user6', 'user6@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user6.jpg'),
       ('user7', 'user7', 'user7@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user7.jpg'),
       ('user8', 'user8', 'user8@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user8.jpg'),
       ('user9', 'user9', 'user9@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user9.jpg'),
       ('user10', 'user10', 'user10@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/user10.jpg');

-- ASSIGN ROLES TO USERS/ADMINS
INSERT INTO user_role
    (user_id, role_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1)
;





