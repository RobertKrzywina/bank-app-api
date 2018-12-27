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