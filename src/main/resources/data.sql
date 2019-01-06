INSERT INTO roles (role_name)
VALUES ('ROLE_HEAD-ADMIN'),
       ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO admins (decoded_bcrypt_password, login, name, password, role_name)
VALUES ('a', 'a', 'JoeH-A', '$2a$04$hSnmEKSm.JsHM8Ob1P5dE.ITNT0JXebLxoKunKPig527avuNI.zlq', 'ROLE_HEAD-ADMIN'),
       ('b', 'b', 'Mia', '$2a$04$sPaqvVQLuWIgOby0eNBaTO5QKKMcYye.bm.4IgDch37loQK9FyYce', 'ROLE_ADMIN'),
       ('c', 'c', 'Tom', '$2a$04$e4wCIpvmmIB5nPq32oTYBONeN6k4B4dgs9nKikL9EtIIXlhigWC6a', 'ROLE_ADMIN'),
       ('d', 'd', 'Tim', '$2a$04$U3GNJhVaBCAFdkfX7.znN.ahk.hH3nwbe.hu58v3SBeAB.iNatW8i', 'ROLE_ADMIN'),
       ('e', 'e', 'Rob', '$2a$04$XW.9LfiE5W5c/JmHQV3YbeKgSIjFc4GDbyLo08armLXtMOnKh1SjG', 'ROLE_ADMIN'),
       ('a2', 'Jacky', 'Jack', '$2a$04$YuvANOF2Zcca7fI18MOvae0Oakw5FMufdb80wl3DVydVlSwmJCu4O', 'ROLE_HEAD-ADMIN');

INSERT INTO admins_roles (admin_admin_id, roles_role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 1);

INSERT INTO user_contact (user_contact_pesel, email, phone_number)
VALUES ('80022136394', 'justin@email.com', '534149781'),
       ('83100949123', 'spencer@gmail.com', '534149782'),
       ('76082787162', 'sarah@iCloud.com', '534149783'),
       ('67020584817', 'iliketoCODE@zombiedev.com', '404404404'),
       ('77071382883', 'kari@emaaail.com', '534149784');

INSERT INTO user_address (user_address_pesel, city, house_number, province, street, zip_code)
VALUES ('80022136394', 'Warszawa', '44', 'Mazowieckie', 'Krakowskie Przedmieście', '00-001'),
       ('83100949123', 'Lublin', '2', 'Lubelskie', 'Graniczna', '02-593'),
       ('76082787162', 'Wroclaw', '32', 'Dolnoslaskie', 'Teczowa', '10-026'),
       ('67020584817', 'Poznan', '99', 'Wielkopolskie', '11 Listopada', '60-106'),
       ('77071382883', 'Szczecin', '59', 'Zachodniopomorskie', 'Akacjowa', '70-004');

INSERT INTO user_bank_account (user_bank_account_pesel, account_number, balance)
VALUES ('80022136394', 'PL38 6304 4308 4918 4834 9838', 4810.01),
       ('83100949123', 'PL62 8058 5353 1332 3091 4284', 8263.63),
       ('76082787162', 'PL46 3539 4580 4736 5776 8890', 2389.09),
       ('67020584817', 'PL91 3018 4529 8669 0250 7367', 5857.27),
       ('77071382883', 'PL85 7529 0532 0943 1086 2148', 3827.22);

INSERT INTO users (pesel, decoded_bcrypt_password, first_name, last_name, password, role_name, user_address_pesel,
                   user_bank_account_pesel, user_contact_pesel)
VALUES ('80022136394', 'RickAndMorty2013', 'Justin', 'Roiland',
        '$2a$04$Yn4i7ManBgwyvsucKhRmcOw1y0cAUwuYULwdgW/F/eVubirWpkFTC', 'ROLE_USER', '80022136394', '80022136394',
        '80022136394'),
       ('83100949123', 'summerSmith123', 'Spencer', 'Grammer',
        '$2a$04$und0GjvFBHt1bukm2p8aGuijU1gCbERfbbui88keDClvaHAzle8o6', 'ROLE_USER', '83100949123', '83100949123',
        '83100949123'),
       ('76082787162', 'bethSmith123', 'Sarah', 'Chalke',
        '$2a$04$oRXzfn1Rzks1RFB3Ub5SyOL4t61rt3jHzNWGsLKacVUR4pddY/UCu', 'ROLE_USER', '76082787162', '76082787162',
        '76082787162'),
       ('67020584817', 'jerrySmith123', 'Chris', 'Parnell',
        '$2a$04$0bB.u1tt6ax92gWedd.SeuZY1cwBwOCew.D0/OOvFlWYDKMv/KgNi', 'ROLE_USER', '67020584817', '67020584817',
        '67020584817'),
       ('77071382883', 'jessica1111', 'Kari', 'Wahlgren',
        '$2a$04$jM0DyGEEGS/p/SN7SUHp3.M30azeTe3zr5eiU7Bi7xPBm7lajkcsi', 'ROLE_USER', '77071382883', '77071382883',
        '77071382883');

INSERT INTO users_roles (user_pesel, roles_role_id)
VALUES ('80022136394', 3),
       ('83100949123', 3),
       ('76082787162', 3),
       ('67020584817', 3),
       ('77071382883', 3);
