INSERT INTO admins (name, login, password, special_password, role_name)
VALUES ('JoeTheHeadAdmin', 'a', 'a', 'a', 'ROLE_HEAD-ADMIN'),
       ('Mia', 'b', 'b', 'b', 'ROLE_ADMIN'),
       ('Tom', 'c', 'c', 'c', 'ROLE_ADMIN'),
       ('Tim', 'd', 'd', 'd', 'ROLE_ADMIN'),
       ('Rob', 'e', 'e', 'e', 'ROLE_ADMIN'),
       ('Jack', 'Jacky', 'a2', 'a2121', 'ROLE_HEAD-ADMIN');

INSERT INTO roles (role_name)
VALUES ('ROLE_HEAD-ADMIN'),
       ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO admins_roles (admin_admin_id, roles_role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 1);

INSERT INTO users (pesel, first_name, last_name, password, role_name)
VALUES ('80022136394', 'Justin', 'Roiland', 'RickAndMorty2013', 'ROLE_USER'),
       ('83100949123', 'Spencer', 'Grammer', 'summerSmith123', 'ROLE_USER'),
       ('76082787162', 'Sarah', 'Chalke', 'bethSmith123', 'ROLE_USER'),
       ('67020584817', 'Chris', 'Parnell', 'jerrySmith123', 'ROLE_USER'),
       ('77071382883', 'Kari', 'Wahlgren', 'jessica1111', 'ROLE_USER');

INSERT INTO users_roles (user_pesel, roles_role_id)
VALUES ('80022136394', 3),
       ('83100949123', 3),
       ('76082787162', 3),
       ('67020584817', 3),
       ('77071382883', 3);