create table ticket
(
    id           bigserial
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

INSERT INTO public.ticket (id, uuid, customer_id, doctor_id, time_slot_id) VALUES (21, 'b1e7342e-aa0d-4a21-b37a-8ed935623dce', 1, 4, 15);
INSERT INTO public.ticket (id, uuid, customer_id, doctor_id, time_slot_id) VALUES (23, 'bdd93d4c-69dd-4564-859f-baf1173e3dfb', 1, 4, 16);
