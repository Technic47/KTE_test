create table customer
(
    id          bigserial
        primary key,
    uuid        uuid,
    birth_date  timestamp(6),
    created     timestamp(6),
    first_name  varchar(255),
    gender      varchar(255)
        constraint customer_gender_check
            check ((gender)::text = ANY
                   ((ARRAY ['MALE'::character varying, 'FEMALE'::character varying, 'UNKNOWN'::character varying])::text[])),
    given_name  varchar(255),
    second_name varchar(255),
    updated     timestamp(6)
)
    using ???;

alter table customer
    owner to postgres;

INSERT INTO public.customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated) VALUES (1, '86172d18-f2f8-41ed-8bf2-a43ca9d8a395', '1990-01-05 00:00:00.000000', '2023-09-19 22:08:57.237000', 'Customer1', 'FEMALE', null, 'Petrov', null);
INSERT INTO public.customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated) VALUES (2, 'c0315004-4a54-493f-bb33-ad828866c8d1', '1990-01-05 00:00:00.000000', '2023-09-19 22:09:01.143000', 'Customer2', 'FEMALE', null, 'Petrov', null);
INSERT INTO public.customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated) VALUES (3, '821a14b0-176c-413e-b7a2-2b0db0e12983', '1990-01-05 00:00:00.000000', '2023-09-19 22:09:03.486000', 'Customer3', 'FEMALE', null, 'Petrov', null);
INSERT INTO public.customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated) VALUES (4, '2cff1782-2365-4de4-890d-022cdb56ef51', '1990-01-05 00:00:00.000000', '2023-09-19 22:09:05.842000', 'Customer4', 'FEMALE', null, 'Petrov', null);
INSERT INTO public.customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated) VALUES (5, 'b2ee6c26-bbe7-4351-ba16-a829c1aa8b53', '1990-01-05 00:00:00.000000', '2023-09-19 22:09:08.250000', 'Customer5', 'FEMALE', null, 'Petrov', null);
