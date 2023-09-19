create table doctor
(
    id          bigserial
        primary key,
    uuid        uuid,
    birth_date  timestamp(6),
    created     timestamp(6),
    first_name  varchar(255),
    gender      varchar(255)
        constraint doctor_gender_check
            check ((gender)::text = ANY
                   ((ARRAY ['MALE'::character varying, 'FEMALE'::character varying, 'UNKNOWN'::character varying])::text[])),
    given_name  varchar(255),
    second_name varchar(255),
    updated     timestamp(6),
    speciality  varchar(255)
)
    using ???;

alter table doctor
    owner to postgres;

INSERT INTO public.doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality) VALUES (1, 'a6c16861-40c3-48cc-8341-78b1060585dd', '1985-01-06 00:00:00.000000', '2023-09-19 22:08:33.247000', 'Doctor1', 'MALE', null, 'Stones', null, 'dentist');
INSERT INTO public.doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality) VALUES (2, 'acde7c2a-05b9-466f-8474-da1f5d93db70', '1985-01-06 00:00:00.000000', '2023-09-19 22:08:35.740000', 'Doctor2', 'MALE', null, 'Stones', null, 'dentist');
INSERT INTO public.doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality) VALUES (3, 'b9897b0c-3d0f-4e9d-8107-65f4a8298f09', '1985-01-06 00:00:00.000000', '2023-09-19 22:08:38.009000', 'Doctor3', 'MALE', null, 'Stones', null, 'dentist');
INSERT INTO public.doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality) VALUES (4, '4b490811-60e6-4339-9670-d289baff36ae', '1985-01-06 00:00:00.000000', '2023-09-19 22:08:41.227000', 'Doctor4', 'MALE', null, 'Stones', null, 'dentist');
INSERT INTO public.doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality) VALUES (5, 'ebde8589-442f-4629-8817-3207b398f783', '1985-01-06 00:00:00.000000', '2023-09-19 22:08:44.609000', 'Doctor5', 'MALE', null, 'Stones', null, 'dentist');
