create table archive_ticket
(
    id                   bigserial
        primary key,
    cabinet_number       integer not null,
    customer_credentials varchar(255),
    customer_id          bigint,
    doctor_credentials   varchar(255),
    doctor_id            bigint,
    finish_time          timestamp(6),
    old_id               bigint,
    start_time           timestamp(6),
    time_slot_id         bigint,
    uuid                 uuid
)
    using ???;

alter table archive_ticket
    owner to postgres;

INSERT INTO public.archive_ticket (id, cabinet_number, customer_credentials, customer_id, doctor_credentials, doctor_id, finish_time, old_id, start_time, time_slot_id, uuid) VALUES (1, 100, 'Customer3 null Petrov', 3, 'Doctor2 null Stones', 2, '2023-10-16 10:30:00.000000', 16, '2023-10-16 10:00:00.000000', 3, 'f199d1de-8cba-4296-83cd-aba08347e434');
INSERT INTO public.archive_ticket (id, cabinet_number, customer_credentials, customer_id, doctor_credentials, doctor_id, finish_time, old_id, start_time, time_slot_id, uuid) VALUES (2, 100, 'Customer3 null Petrov', 3, 'Doctor1 null Stones', 1, '2023-10-16 12:00:00.000000', 19, '2023-10-16 11:30:00.000000', 6, '7c7f212f-e0d4-450c-95cb-f8b741823c5a');
INSERT INTO public.archive_ticket (id, cabinet_number, customer_credentials, customer_id, doctor_credentials, doctor_id, finish_time, old_id, start_time, time_slot_id, uuid) VALUES (3, 100, 'Customer3 null Petrov', 3, 'Doctor4 null Stones', 4, '2023-10-16 14:00:00.000000', 20, '2023-10-16 13:30:00.000000', 10, '54482374-d0fd-46fc-a249-b43a402b740d');
