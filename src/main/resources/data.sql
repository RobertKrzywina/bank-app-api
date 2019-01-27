INSERT INTO roles (role_name)
VALUES ('ROLE_HEAD-ADMIN'),
       ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO admins (name, login, password, decoded_bcrypt_password, role_name)
VALUES ('JoeH-A', 'a', '$2a$04$hSnmEKSm.JsHM8Ob1P5dE.ITNT0JXebLxoKunKPig527avuNI.zlq', 'a', 'ROLE_HEAD-ADMIN'),
       ('Mia', 'b', '$2a$04$sPaqvVQLuWIgOby0eNBaTO5QKKMcYye.bm.4IgDch37loQK9FyYce', 'b', 'ROLE_ADMIN'),
       ('Tom', 'c', '$2a$04$e4wCIpvmmIB5nPq32oTYBONeN6k4B4dgs9nKikL9EtIIXlhigWC6a', 'c', 'ROLE_ADMIN'),
       ('Tim', 'd', '$2a$04$U3GNJhVaBCAFdkfX7.znN.ahk.hH3nwbe.hu58v3SBeAB.iNatW8i', 'd', 'ROLE_ADMIN'),
       ('Rob', 'e', '$2a$04$XW.9LfiE5W5c/JmHQV3YbeKgSIjFc4GDbyLo08armLXtMOnKh1SjG', 'e', 'ROLE_ADMIN'),
       ('Jack', 'Jacky', '$2a$04$YuvANOF2Zcca7fI18MOvae0Oakw5FMufdb80wl3DVydVlSwmJCu4O', 'a2', 'ROLE_HEAD-ADMIN');

INSERT INTO admins_roles (admin_admin_id, roles_role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 1);

INSERT INTO contacts (contact_owner_pesel, email, phone_number)
VALUES ('80022136394', 'justin@email.com', '534149781'),
       ('83100949123', 'spencer@gmail.com', '534149782'),
       ('76082787162', 'sarah@iCloud.com', '534149783'),
       ('67020584817', 'iliketoCODE@zombiedev.com', '404404404'),
       ('77071382883', 'kari@emaaail.com', '534149784');

INSERT INTO addresses (address_owner_pesel, province, city, zip_code, street, house_number)
VALUES ('80022136394', 'Mazowieckie', 'Warszawa', '00-001', 'Krakowskie Przedmieście', '44'),
       ('83100949123', 'Lubelskie', 'Lublin', '02-593', 'Graniczna', '2'),
       ('76082787162', 'Dolnoslaskie', 'Wroclaw', '10-026', 'Teczowa', '32'),
       ('67020584817', 'Wielkopolskie', 'Poznan', '60-106', '11 Listopada', '99'),
       ('77071382883', 'Zachodniopomorskie', 'Szczecin', '70-004', 'Akacjowa', '59');

INSERT INTO bank_accounts (bank_account_owner_pesel, account_number, account_balance)
VALUES ('80022136394', 'PL38 6304 4308 4918 4834 9838', 4810.01),
       ('83100949123', 'PL62 8058 5353 1332 3091 4284', 8263.63),
       ('76082787162', 'PL46 3539 4580 4736 5776 8890', 2389.09),
       ('67020584817', 'PL91 3018 4529 8669 0250 7367', 5857.27),
       ('77071382883', 'PL85 7529 0532 0943 1086 2148', 3827.22);

INSERT INTO users (pesel, user_contact_pesel, user_address_pesel, user_bank_account_pesel,
                   first_name, last_name, password,
                   decoded_bcrypt_password, role_name)
VALUES ('80022136394', '80022136394', '80022136394', '80022136394',
        'Justin', 'Roiland', '$2a$04$Yn4i7ManBgwyvsucKhRmcOw1y0cAUwuYULwdgW/F/eVubirWpkFTC',
        'RickAndMorty2013', 'ROLE_USER'),
       ('83100949123', '83100949123', '83100949123', '83100949123',
        'Spencer', 'Grammer', '$2a$04$und0GjvFBHt1bukm2p8aGuijU1gCbERfbbui88keDClvaHAzle8o6',
        'summerSmith123', 'ROLE_USER'),
       ('76082787162', '76082787162', '76082787162', '76082787162',
        'Sarah', 'Chalke', '$2a$04$oRXzfn1Rzks1RFB3Ub5SyOL4t61rt3jHzNWGsLKacVUR4pddY/UCu',
        'bethSmith123', 'ROLE_USER'),
       ('67020584817', '67020584817', '67020584817', '67020584817',
        'Christ', 'Parnell', '$2a$04$0bB.u1tt6ax92gWedd.SeuZY1cwBwOCew.D0/OOvFlWYDKMv/KgNi',
        'jerrySmith123', 'ROLE_USER'),
       ('77071382883', '77071382883', '77071382883', '77071382883',
        'Kari', 'Wahlgren', '$2a$04$jM0DyGEEGS/p/SN7SUHp3.M30azeTe3zr5eiU7Bi7xPBm7lajkcsi',
        'jessica1111', 'ROLE_USER');

INSERT INTO users_roles (user_pesel, roles_role_id)
VALUES ('80022136394', 3),
       ('83100949123', 3),
       ('76082787162', 3),
       ('67020584817', 3),
       ('77071382883', 3);

INSERT INTO transactions (date_of_completion, title, description, amount,
                          sender_bank_account_number, receiver_bank_account_number, bank_account_owner_pesel)
VALUES ('2019-01-04 07:03:24.482', 'Outstanding money', 'Money from Justin to Sarah', 1000.45,
        'PL38 6304 4308 4918 4834 9838', 'PL46 3539 4580 4736 5776 8890', '80022136394'),
       ('2019-01-12 12:12:12.12', 'Gift', 'Money from Justin to Chris', 1221.11,
        'PL38 6304 4308 4918 4834 9838', 'PL91 3018 4529 8669 0250 7367', '80022136394'),
       ('2019-01-13 03:22:24.482', 'Rent for the house', 'Money from Chris to Spencer', 1240.22,
        'PL91 3018 4529 8669 0250 7367', 'PL62 8058 5353 1332 3091 4284', '67020584817'),
       ('2019-01-14 23:01:44.235', 'Gift', 'Money from Kari to Sarah', 2389.09,
        'PL85 7529 0532 0943 1086 2148', 'PL46 3539 4580 4736 5776 8890', '77071382883'),
       ('2019-01-15 16:03:27.954', 'Money for the MacBook', 'Money from Sarah to Kari', 3827.22,
        'PL46 3539 4580 4736 5776 8890', 'PL85 7529 0532 0943 1086 2148', '76082787162');

