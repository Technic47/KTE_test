create table time_slot
(
    id          bigint  not null
        primary key,
    uuid        uuid,
    finish_time timestamp(6),
    occupied    boolean not null,
    start_time  timestamp(6),
    cabinet_id  bigint
        constraint fka9kqajrlt47eeaykelfhyu7wm
            references cabinet,
    ticket_id   bigint
        constraint uk_d70m0jxb0vosvic7e9bumxyai
            unique
        constraint fk5au3l3wefjav6vq7aoaehyqx5
            references ticket
)
    using ???;

alter table time_slot
    owner to postgres;

create unique index time_slot_pkey
    on time_slot using ??? (id);

create unique index uk_d70m0jxb0vosvic7e9bumxyai
    on time_slot using ??? (ticket_id);

INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10902, 'b656f70d-606c-4e1f-a093-8c1f0120feb8', '2023-10-16 09:30:00.000000', false, '2023-10-16 09:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10903, '226278c5-4dca-4245-8d6e-127bf60da137', '2023-10-16 10:00:00.000000', false, '2023-10-16 09:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10904, 'c28b1dac-c60f-4b7c-abee-9db5bc42e1ab', '2023-10-16 10:30:00.000000', false, '2023-10-16 10:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10905, 'f624091d-4be5-4b2c-ad1b-699c93dd5a92', '2023-10-16 11:00:00.000000', false, '2023-10-16 10:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10906, '1c7fb3d2-f471-48da-8b7a-d9f3748276f8', '2023-10-16 11:30:00.000000', false, '2023-10-16 11:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10907, 'c63edc74-1ec3-44f9-9567-954edfaca725', '2023-10-16 12:00:00.000000', false, '2023-10-16 11:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10908, 'c3b1b774-598f-4282-a32b-6d1d3d67f6ba', '2023-10-16 12:30:00.000000', false, '2023-10-16 12:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10909, 'a4218336-cc87-4f16-8110-283fe26474fd', '2023-10-16 13:00:00.000000', false, '2023-10-16 12:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10910, '2e55acc7-b265-4a83-9380-353f13f29161', '2023-10-16 13:30:00.000000', false, '2023-10-16 13:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10911, 'f17d9377-17b4-45dd-8871-2e6fb205eab6', '2023-10-16 14:00:00.000000', false, '2023-10-16 13:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10912, 'ffd7706c-55bc-42f2-bedc-24e26d0da766', '2023-10-16 14:30:00.000000', false, '2023-10-16 14:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10913, 'aa9d78f3-276c-438f-a77c-4e26272e1e9d', '2023-10-16 15:00:00.000000', false, '2023-10-16 14:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10914, 'fe46baf9-568d-456e-8f4d-dbbc528b588d', '2023-10-16 15:30:00.000000', false, '2023-10-16 15:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10915, '472edf42-28e7-440a-96dc-959e68054e47', '2023-10-16 16:00:00.000000', false, '2023-10-16 15:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10916, 'cd95e58d-ba26-4c9b-a85b-9c2e1dc6bd6c', '2023-10-16 16:30:00.000000', false, '2023-10-16 16:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10917, '78732e42-2e67-4911-83d3-5045aaa572b2', '2023-10-16 17:00:00.000000', false, '2023-10-16 16:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10918, '487b1e11-1e9c-4025-9f45-49b35e4761fd', '2023-10-16 17:30:00.000000', false, '2023-10-16 17:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10919, 'fce4fe52-59d7-4f36-9967-7bdbf0a9c037', '2023-10-16 18:00:00.000000', false, '2023-10-16 17:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10920, '8e3b93de-399c-498e-9f7d-e2fe44280bb3', '2023-10-16 09:30:00.000000', false, '2023-10-16 09:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10921, '783f785b-6779-4be5-bed6-94ba72b50e04', '2023-10-16 10:00:00.000000', false, '2023-10-16 09:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10922, 'e3d40961-c970-4d39-b2cd-e421c33223bc', '2023-10-16 10:30:00.000000', false, '2023-10-16 10:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10923, 'ab5bb9c4-d51e-47d4-9da7-f01f42bfd2f7', '2023-10-16 11:00:00.000000', false, '2023-10-16 10:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10924, '21685bcd-c093-4956-8459-7a9f836fc1b6', '2023-10-16 11:30:00.000000', false, '2023-10-16 11:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10925, '027cea24-f7e4-4c50-8819-0d6c30b6c4df', '2023-10-16 12:00:00.000000', false, '2023-10-16 11:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10926, '17ac62ba-1fae-4068-a58b-c8c64238b918', '2023-10-16 12:30:00.000000', false, '2023-10-16 12:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10927, '5d873be0-b4f8-43c2-b058-2c077202b41a', '2023-10-16 13:00:00.000000', false, '2023-10-16 12:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10928, '6555222e-c38a-43a9-8256-eabd145d4ea5', '2023-10-16 13:30:00.000000', false, '2023-10-16 13:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10929, '7b86816b-bae0-4420-a2ca-3c966bc6502d', '2023-10-16 14:00:00.000000', false, '2023-10-16 13:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10930, '463470a5-521d-4a3b-bd2f-d5a42e6742ef', '2023-10-16 14:30:00.000000', false, '2023-10-16 14:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10931, '12e9c8a7-eb1e-4d98-9175-ed8fcc8615fc', '2023-10-16 15:00:00.000000', false, '2023-10-16 14:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10932, '326e7fe6-982d-434e-90ac-f040a794d75c', '2023-10-16 15:30:00.000000', false, '2023-10-16 15:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10933, '65caeef7-87d4-4a64-8e41-6c9e20421898', '2023-10-16 16:00:00.000000', false, '2023-10-16 15:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10934, 'dac39ecc-b2db-48bc-b2a3-a193bde06715', '2023-10-16 16:30:00.000000', false, '2023-10-16 16:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10935, 'ae05de85-c3a9-4a7a-91ee-b94991f426e4', '2023-10-16 17:00:00.000000', false, '2023-10-16 16:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10936, 'af5fa206-be99-43a5-803a-8eccb6d0176e', '2023-10-16 17:30:00.000000', false, '2023-10-16 17:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10937, 'dfdcbfc6-4296-4df8-ac4d-77aa15ab3b3c', '2023-10-16 18:00:00.000000', false, '2023-10-16 17:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10939, '172237ef-0c41-4724-bbd3-e37af09236dd', '2023-10-16 10:00:00.000000', false, '2023-10-16 09:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10940, '6cd81493-7917-408d-9017-e4acb6205483', '2023-10-16 10:30:00.000000', false, '2023-10-16 10:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10941, '2051187a-3e17-4c15-9275-9368af98589c', '2023-10-16 11:00:00.000000', false, '2023-10-16 10:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10942, 'bb6b0e60-e276-4260-989d-2995a7048f9e', '2023-10-16 11:30:00.000000', false, '2023-10-16 11:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10943, 'cfdb599c-d9ad-4de9-88bf-9569bdfce2cb', '2023-10-16 12:00:00.000000', false, '2023-10-16 11:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10944, 'a28907f7-7119-498c-a3da-97f2656849df', '2023-10-16 12:30:00.000000', false, '2023-10-16 12:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10945, '67ab532c-57ec-4369-bc27-83bec0d2d05b', '2023-10-16 13:00:00.000000', false, '2023-10-16 12:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10946, 'a8afd4d0-9747-440c-9ddf-28c6d937fc9a', '2023-10-16 13:30:00.000000', false, '2023-10-16 13:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10947, '7cc85229-33fe-4b29-af62-f0b86d734448', '2023-10-16 14:00:00.000000', false, '2023-10-16 13:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10948, '965e68bc-700c-4d73-87da-5c891c4b0420', '2023-10-16 14:30:00.000000', false, '2023-10-16 14:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10949, '05dcf116-cd12-41f0-bc71-a58b4385ad08', '2023-10-16 15:00:00.000000', false, '2023-10-16 14:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10950, '1a1938e7-dd18-4663-9033-a6f93a3083d8', '2023-10-16 15:30:00.000000', false, '2023-10-16 15:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10951, '3d5888c2-ad40-4c64-91e9-38e870ecb3e5', '2023-10-16 16:00:00.000000', false, '2023-10-16 15:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10952, '2dcf29bb-2782-4fbb-9493-53841ec03720', '2023-10-16 16:30:00.000000', false, '2023-10-16 16:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10953, '0d484ccb-cdf6-4ceb-b327-f6417cd0a335', '2023-10-16 17:00:00.000000', false, '2023-10-16 16:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10954, 'd4f339e9-9188-4f96-9ed5-ec78a534d2bd', '2023-10-16 17:30:00.000000', false, '2023-10-16 17:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10955, '5ee11acd-5baa-44f8-9fbe-965f6ec29ee0', '2023-10-16 18:00:00.000000', false, '2023-10-16 17:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11002, 'fb82bb94-43d6-45af-87f1-9a70f4d8d168', '2023-11-16 09:30:00.000000', false, '2023-11-16 09:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11003, 'a7c09e8b-1939-4d83-bac0-4defc3a0c498', '2023-11-16 10:00:00.000000', false, '2023-11-16 09:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11004, 'ba993eee-04f1-4768-a0d0-793f99f8c032', '2023-11-16 10:30:00.000000', false, '2023-11-16 10:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11005, 'ac06b54d-c4b4-4b74-976e-548da6d160ed', '2023-11-16 11:00:00.000000', false, '2023-11-16 10:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11006, '043e9151-9086-44ff-aa69-a78aeb4680f9', '2023-11-16 11:30:00.000000', false, '2023-11-16 11:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11007, '10bdf220-3815-4d1f-90c8-f612ff995a9e', '2023-11-16 12:00:00.000000', false, '2023-11-16 11:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11008, '621e7baa-7934-431d-afce-b9be358496e7', '2023-11-16 12:30:00.000000', false, '2023-11-16 12:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11009, 'c5f92f4e-3885-4141-854f-6a18544ad10b', '2023-11-16 13:00:00.000000', false, '2023-11-16 12:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11010, '97c309e2-3cdf-463a-92a6-561e172ca3b1', '2023-11-16 13:30:00.000000', false, '2023-11-16 13:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11011, 'f40eb40c-6814-4bff-9537-2172675d29de', '2023-11-16 14:00:00.000000', false, '2023-11-16 13:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11012, 'fb086c48-9126-45f0-b8c5-751443cd390d', '2023-11-16 14:30:00.000000', false, '2023-11-16 14:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11013, '7db27eec-5b95-47a5-889e-05b7f555300e', '2023-11-16 15:00:00.000000', false, '2023-11-16 14:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11014, '15496466-6792-4da8-aac9-bf6f3e150ca2', '2023-11-16 15:30:00.000000', false, '2023-11-16 15:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11015, '0872b717-7c34-4dec-aa29-326768dfe6a8', '2023-11-16 16:00:00.000000', false, '2023-11-16 15:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11016, 'ef71d907-3dcd-408a-845e-77ddb348f00f', '2023-11-16 16:30:00.000000', false, '2023-11-16 16:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11017, '9aeb840a-8a68-4d05-bf04-51f4df289b9d', '2023-11-16 17:00:00.000000', false, '2023-11-16 16:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11018, '0ba0286d-6ca1-496a-8119-98d9a5681dfb', '2023-11-16 17:30:00.000000', false, '2023-11-16 17:00:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11019, 'cdb17ce4-2195-4276-8332-ec52dcd73dfb', '2023-11-16 18:00:00.000000', false, '2023-11-16 17:30:00.000000', 4, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10938, 'db57382e-631a-4553-a2b2-00d1a9b925f1', '2023-10-16 09:30:00.000000', true, '2023-10-16 09:00:00.000000', 4, 152);
