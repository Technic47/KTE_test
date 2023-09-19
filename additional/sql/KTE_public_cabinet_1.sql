create table cabinet
(
    id     bigserial
        primary key,
    uuid   uuid,
    number integer
        constraint uk_33cry5whh1gxlieosptyhxo8f
            unique
)
    using ???;

alter table cabinet
    owner to postgres;

INSERT INTO public.cabinet (id, uuid, number) VALUES (1, '4babb257-18ea-40f3-b8b6-23c6df65fa8e', 100);
INSERT INTO public.cabinet (id, uuid, number) VALUES (2, 'f8e03d9c-f465-4f48-94c6-8a8f61265123', 200);
INSERT INTO public.cabinet (id, uuid, number) VALUES (3, '6aab9545-ab11-4320-8f6a-da246a8882eb', 300);
INSERT INTO public.cabinet (id, uuid, number) VALUES (4, 'e3752299-b7ff-4514-bb90-bd089abd4472', 400);
