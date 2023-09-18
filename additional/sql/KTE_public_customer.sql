create table customer
(
    id          bigint not null
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

create unique index customer_pkey
    on customer using ??? (id);

INSERT INTO public.customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated) VALUES (2, 'cbb21ab3-f712-4934-9136-bf8a1454fced', '1999-01-15 00:00:00.000000', '2023-09-18 11:46:37.768000', 'Anna', 'FEMALE', null, 'Kukushkina', null);
INSERT INTO public.customer (id, uuid, birth_date, created, first_name, gender, given_name, second_name, updated) VALUES (3, 'ebb2ae81-2783-4e13-988d-cc92970a9ebc', '1990-01-05 00:00:00.000000', '2023-09-18 11:47:08.577000', 'Ala', 'FEMALE', null, 'Petrova', null);
