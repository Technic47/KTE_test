TRUNCATE cabinet RESTART IDENTITY CASCADE;
TRUNCATE cabinet_timeslots RESTART IDENTITY CASCADE;
TRUNCATE customer RESTART IDENTITY CASCADE;
TRUNCATE customer_tickets RESTART IDENTITY CASCADE;
TRUNCATE doctor RESTART IDENTITY CASCADE;
TRUNCATE doctor_tickets RESTART IDENTITY CASCADE;
TRUNCATE ticket RESTART IDENTITY CASCADE;
TRUNCATE time_slot RESTART IDENTITY CASCADE;
TRUNCATE user_model RESTART IDENTITY CASCADE;
TRUNCATE user_role RESTART IDENTITY CASCADE;

INSERT INTO cabinet (id, uuid, number)
VALUES (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 100),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 200);

INSERT INTO customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated)
VALUES (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Ivan',
        'MALE',
        '', 'Ivanov', '2023-06-20'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Alex',
        'MALE',
        '', 'Ivanov', '2023-06-20'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Kolia',
        'MALE',
        '', 'Ivanov', '2023-06-20'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Ivan',
        'MALE',
        '', 'Ivanov', '2023-06-20'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Kolia',
        'MALE',
        '', 'Ivanov', '2023-06-20'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Anna',
        'FEMALE',
        '', 'Ivanova', '2023-06-20'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Ivan',
        'MALE',
        '', 'Ivanov', '2023-06-20'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Kolia',
        'MALE',
        '', 'Ivanov', '2023-06-20');

INSERT INTO doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality)
VALUES (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Ivan',
        'MALE',
        '', 'Ivanov', '2023-06-20', 'dentist'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Alex',
        'MALE',
        '', 'Ivanov', '2023-06-20', 'dentist'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Ivan',
        'MALE',
        '', 'Ivanov', '2023-06-20', 'dentist'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), '1990-06-20', '2023-06-20', 'Ivan',
        'MALE',
        '', 'Ivanov', '2023-06-20', 'dentist');

INSERT INTO user_model (id, uuid, active, created, email, enabled, password, updated, username)
VALUES (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), true, '1990-06-20', 'email@mail.il',
        true,
        'pass', '2023-06-20', 'pavel'),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), true, '1990-06-20', 'email2@mail.il',
        true,
        'pass', '2023-06-20', 'user');

INSERT INTO user_role (user_id, status)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id)
VALUES (DEFAULT, 'b656f70d-606c-4e1f-a093-8c1f0120feb8', '2023-10-16 09:30:00', true,
        '2023-10-16 09:00:00', 1),
       (DEFAULT, '226278c5-4dca-4245-8d6e-127bf60da137', '2023-10-16 10:00:00', true,
        '2023-10-16 09:30:00', 1),
       (DEFAULT, 'c28b1dac-c60f-4b7c-abee-9db5bc42e1ab', '2023-10-16 10:30:00', true,
        '2023-10-16 10:00:00', 1),
       (DEFAULT, 'f624091d-4be5-4b2c-ad1b-699c93dd5a92', '2023-10-16 11:00:00', false,
        '2023-10-16 10:30:00', 1),
       (DEFAULT, '1c7fb3d2-f471-48da-8b7a-d9f3748276f8', '2023-10-16 11:30:00', true,
        '2023-10-16 11:00:00', 1),
       (DEFAULT, 'c63edc74-1ec3-44f9-9567-954edfaca725', '2023-10-16 12:00:00', true,
        '2023-10-16 11:30:00', 2),
       (DEFAULT, 'c3b1b774-598f-4282-a32b-6d1d3d67f6ba', '2023-10-16 12:30:00', true,
        '2023-10-16 12:00:00', 1),
       (DEFAULT, 'a4218336-cc87-4f16-8110-283fe26474fd', '2023-10-16 13:00:00', false,
        '2023-10-16 12:30:00', 1),
       (DEFAULT, '2e55acc7-b265-4a83-9380-353f13f29161', '2023-10-16 13:30:00', false,
        '2023-10-16 13:00:00', 1),
       (DEFAULT, 'f17d9377-17b4-45dd-8871-2e6fb205eab6', '2023-10-16 14:00:00', true,
        '2023-10-16 13:30:00', 1),
       (DEFAULT, 'ffd7706c-55bc-42f2-bedc-24e26d0da766', '2023-10-16 14:30:00', true,
        '2023-10-16 14:00:00', 1),
       (DEFAULT, 'aa9d78f3-276c-438f-a77c-4e26272e1e9d', '2023-10-16 15:00:00', true,
        '2023-10-16 14:30:00', 1),
       (DEFAULT, 'fe46baf9-568d-456e-8f4d-dbbc528b588d', '2023-10-16 15:30:00', false,
        '2023-10-16 15:00:00', 1),
       (DEFAULT, '472edf42-28e7-440a-96dc-959e68054e47', '2023-10-16 16:00:00', false,
        '2023-10-16 15:30:00', 1),
       (DEFAULT, 'cd95e58d-ba26-4c9b-a85b-9c2e1dc6bd6c', '2023-10-16 16:30:00', true,
        '2023-10-16 16:00:00', 1),
       (DEFAULT, '78732e42-2e67-4911-83d3-5045aaa572b2', '2023-10-16 17:00:00', true,
        '2023-10-16 16:30:00', 1),
       (DEFAULT, '487b1e11-1e9c-4025-9f45-49b35e4761fd', '2023-10-16 17:30:00', true,
        '2023-10-16 17:00:00', 1),
       (DEFAULT, 'fce4fe52-59d7-4f36-9967-7bdbf0a9c037', '2023-10-16 18:00:00', false,
        '2023-10-16 17:30:00', 1);

INSERT INTO cabinet_timeslots (cabinet_id, timeslots_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (1, 12),
       (1, 13),
       (1, 14),
       (1, 15),
       (1, 16),
       (1, 17),
       (1, 18);

INSERT INTO ticket (id, uuid, customer_id, doctor_id, time_slot_id)
VALUES (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 1, 1, 1),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 2, 1, 5),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 3, 2, 10),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 4, 2, 15),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 5, 1, 2),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 6, 1, 6),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 7, 2, 11),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 8, 2, 16),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 1, 1, 3),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 2, 1, 7),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 3, 2, 12),
       (DEFAULT, (SELECT uuid_in(md5(random()::text || random()::text)::cstring)), 4, 2, 17);

INSERT INTO customer_tickets (customer_id, tickets_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (1, 9),
       (2, 10),
       (3, 11),
       (4, 12);

INSERT INTO doctor_tickets (doctor_id, tickets_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (1, 5),
       (1, 6),
       (2, 7),
       (2, 8),
       (1, 9),
       (1, 10),
       (2, 11),
       (2, 12);