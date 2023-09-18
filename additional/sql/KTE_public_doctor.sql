create table doctor
(
    id          bigint not null
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

create unique index doctor_pkey
    on doctor using ??? (id);

INSERT INTO public.doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality) VALUES (252, '8a95ad4f-afe2-4890-92d8-98d18d8e950e', '1999-01-15 00:00:00.000000', '2023-09-18 11:45:47.364000', 'Mike', 'MALE', null, 'Kukushkin', null, 'neiro');
INSERT INTO public.doctor (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated, speciality) VALUES (253, 'e7a007de-62d9-4979-90e1-9e4b69f31811', '1985-01-06 00:00:00.000000', '2023-09-18 11:45:59.603000', 'Alex', 'MALE', null, 'Stone', null, 'dentist');
