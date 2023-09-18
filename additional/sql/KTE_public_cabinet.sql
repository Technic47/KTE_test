create table cabinet
(
    id     bigint not null
        primary key,
    uuid   uuid,
    number integer
        constraint uk_33cry5whh1gxlieosptyhxo8f
            unique
)
    using ???;

alter table cabinet
    owner to postgres;

create unique index cabinet_pkey
    on cabinet using ??? (id);

create unique index uk_33cry5whh1gxlieosptyhxo8f
    on cabinet using ??? (number);

INSERT INTO public.cabinet (id, uuid, number) VALUES (1, '336950b8-ca4a-4044-8d04-9ae5d079a38b', 100);
INSERT INTO public.cabinet (id, uuid, number) VALUES (2, '02b4a896-85b3-4a25-a2ec-95abd1ceb4fc', 101);
INSERT INTO public.cabinet (id, uuid, number) VALUES (3, 'f7044b9f-0827-4f9e-bb1a-9444e706495d', 102);
INSERT INTO public.cabinet (id, uuid, number) VALUES (4, '064b8fdc-3764-44c7-8c34-03dee11cbae0', 200);
INSERT INTO public.cabinet (id, uuid, number) VALUES (5, '307c053a-5d65-499e-86b4-932f00826681', 201);
INSERT INTO public.cabinet (id, uuid, number) VALUES (6, 'b2dec002-a597-4744-bd5f-cb8c099283d8', 202);
INSERT INTO public.cabinet (id, uuid, number) VALUES (7, '9e910b43-ff4d-4fe0-8dca-26fcc3576282', 203);
