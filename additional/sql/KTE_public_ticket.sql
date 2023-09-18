create table ticket
(
    id           bigint not null
        primary key,
    uuid         uuid,
    customer_id  bigint
        constraint fkmli0eqrecnnqvdgv3kcx7d9m8
            references customer,
    doctor_id    bigint
        constraint fk6x8nki7iti30862ad0pc0p9kv
            references doctor,
    time_slot_id bigint
        constraint uk_pbwuahtfjy1okqpcy32mh7v3b
            unique
        constraint fkeuycf8x4xvnl5p69prkys6m5r
            references time_slot
)
    using ???;

alter table ticket
    owner to postgres;

create unique index ticket_pkey
    on ticket using ??? (id);

create unique index uk_pbwuahtfjy1okqpcy32mh7v3b
    on ticket using ??? (time_slot_id);

INSERT INTO public.ticket (id, uuid, customer_id, doctor_id, time_slot_id) VALUES (1, 'ef4087f4-f09f-45b8-ad19-2c46cf93bbfd', 2, 252, 10935);
INSERT INTO public.ticket (id, uuid, customer_id, doctor_id, time_slot_id) VALUES (3, '55948801-d060-408b-974a-16ad30c52162', 3, 252, 10934);
INSERT INTO public.ticket (id, uuid, customer_id, doctor_id, time_slot_id) VALUES (53, '857565ff-8e05-4ec5-8481-c411af92b2be', 3, 252, 10936);
INSERT INTO public.ticket (id, uuid, customer_id, doctor_id, time_slot_id) VALUES (102, 'b9181e88-085a-4f65-9e6b-a3ae2e485f02', 2, 252, 10937);
INSERT INTO public.ticket (id, uuid, customer_id, doctor_id, time_slot_id) VALUES (152, '7cbd0510-4e8f-452f-908f-2fc1d51bc2bf', 2, 252, 10938);
