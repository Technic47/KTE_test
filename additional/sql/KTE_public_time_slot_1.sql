create table time_slot
(
    id          bigserial
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

INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (4, 'c8a9b127-6d93-4f38-8b55-91c06b4b6078', '2023-10-16 11:00:00.000000', false, '2023-10-16 10:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (5, '8085ee6d-0b7c-4d1e-b8de-76edb34170f9', '2023-10-16 11:30:00.000000', false, '2023-10-16 11:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (7, '9d29f0a7-a4a9-411d-9387-1469b4366adf', '2023-10-16 12:30:00.000000', false, '2023-10-16 12:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (9, '147ffb43-d607-4508-b3e5-62964c6cbf09', '2023-10-16 13:30:00.000000', false, '2023-10-16 13:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (11, 'a2c1c8c3-4b15-4821-beb5-88fbd2bb92e1', '2023-10-16 14:30:00.000000', false, '2023-10-16 14:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (12, '42db5433-1f5e-497d-83ab-d02fd27681a4', '2023-10-16 15:00:00.000000', false, '2023-10-16 14:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (13, '12c11e8c-381d-4cf8-8d6b-cbf95cc359c6', '2023-10-16 15:30:00.000000', false, '2023-10-16 15:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (14, '71444835-45aa-4812-a4cf-b3e66b054221', '2023-10-16 16:00:00.000000', false, '2023-10-16 15:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (17, '6f383f14-d590-48b1-b868-5fd16519a7ec', '2023-10-16 17:30:00.000000', false, '2023-10-16 17:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (18, '9769c8a3-59db-4dfa-a36f-ef721460db3e', '2023-10-16 18:00:00.000000', false, '2023-10-16 17:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (19, 'd096800c-9ff7-4e31-a1b1-85c766e0d8d5', '2023-10-16 09:30:00.000000', false, '2023-10-16 09:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (20, '6d3ea9dc-3b92-481e-ad73-055cd2ffd0c0', '2023-10-16 10:00:00.000000', false, '2023-10-16 09:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (21, 'a43bd3bc-09c4-4daf-9e03-896eeb97b7ff', '2023-10-16 10:30:00.000000', false, '2023-10-16 10:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (22, '3944b3ff-d66d-444a-b331-5b3832e47bbd', '2023-10-16 11:00:00.000000', false, '2023-10-16 10:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (23, 'a0939887-f898-4831-9dfa-c1bc6eb8e55e', '2023-10-16 11:30:00.000000', false, '2023-10-16 11:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (24, 'bd2ba4b8-3d15-4208-84c3-7f7460e6b522', '2023-10-16 12:00:00.000000', false, '2023-10-16 11:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (25, '3b84e511-a667-4c18-a4f7-275374292f58', '2023-10-16 12:30:00.000000', false, '2023-10-16 12:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (26, '739505b5-7436-4325-9781-117df0f74659', '2023-10-16 13:00:00.000000', false, '2023-10-16 12:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (27, '88871085-5670-4acc-a7b9-9ed1f908c9ea', '2023-10-16 13:30:00.000000', false, '2023-10-16 13:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (28, '3147245a-f33b-42e4-8b67-70b0e025532e', '2023-10-16 14:00:00.000000', false, '2023-10-16 13:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (29, 'd2d00a91-6072-43b2-821c-f7de70357376', '2023-10-16 14:30:00.000000', false, '2023-10-16 14:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (30, 'b5defcd3-d509-4849-aa9c-23432db35392', '2023-10-16 15:00:00.000000', false, '2023-10-16 14:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (31, '223ba55e-16eb-4882-8f37-5d1851adf256', '2023-10-16 15:30:00.000000', false, '2023-10-16 15:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (32, 'de1be78e-99f7-48e7-9895-85bf5e02d3bd', '2023-10-16 16:00:00.000000', false, '2023-10-16 15:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (33, 'a217f6f7-5cb2-4c63-aa7d-5cb15553a926', '2023-10-16 16:30:00.000000', false, '2023-10-16 16:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (34, 'ce23432c-2b8b-480f-84a9-ede5296aa782', '2023-10-16 17:00:00.000000', false, '2023-10-16 16:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (35, '8c446da2-7c16-4a8e-89b3-bcb16133701d', '2023-10-16 17:30:00.000000', false, '2023-10-16 17:00:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (36, '36d8b09a-c96d-4c90-baf3-3f43ba316585', '2023-10-16 18:00:00.000000', false, '2023-10-16 17:30:00.000000', 2, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (37, 'd2e76830-e002-4b50-9f2b-ec0404d4d1e9', '2023-10-16 09:30:00.000000', false, '2023-10-16 09:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (38, 'd27baa63-1f1f-4f21-973f-2eaa4285b515', '2023-10-16 10:00:00.000000', false, '2023-10-16 09:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (39, 'bb5407d3-417d-4133-959d-921e1c9939d9', '2023-10-16 10:30:00.000000', false, '2023-10-16 10:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (40, '65b6e844-4247-4502-833c-2318d459f652', '2023-10-16 11:00:00.000000', false, '2023-10-16 10:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (41, '73201825-033b-4f8a-9034-09d5ac46273e', '2023-10-16 11:30:00.000000', false, '2023-10-16 11:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (42, 'b0883857-f677-49e5-aa03-64fbe96844ba', '2023-10-16 12:00:00.000000', false, '2023-10-16 11:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (43, 'cdb7ebdd-116d-4d6c-a4f4-20ee0d62e292', '2023-10-16 12:30:00.000000', false, '2023-10-16 12:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (44, '92a27a19-bb37-4009-80ec-36bbea1a407a', '2023-10-16 13:00:00.000000', false, '2023-10-16 12:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (45, '88e1e3ee-ca5f-4faa-b40d-87be54fc689c', '2023-10-16 13:30:00.000000', false, '2023-10-16 13:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (46, '14b629cc-dd2c-49ae-922a-006e60eaa39b', '2023-10-16 14:00:00.000000', false, '2023-10-16 13:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (47, '195de24a-9baf-44e4-a200-7ece318a8976', '2023-10-16 14:30:00.000000', false, '2023-10-16 14:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (48, 'cd5e857a-5e02-452f-92d4-1e524205af6a', '2023-10-16 15:00:00.000000', false, '2023-10-16 14:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (49, 'eeb8ed3d-0b72-495e-a9b6-5812a9c41029', '2023-10-16 15:30:00.000000', false, '2023-10-16 15:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (50, '354670ec-ae71-4bbe-8d7e-4b5332cd4bbc', '2023-10-16 16:00:00.000000', false, '2023-10-16 15:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (51, 'b6124d97-7a72-4ee4-a9b6-30806827d014', '2023-10-16 16:30:00.000000', false, '2023-10-16 16:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (52, '5a1ae5c5-23d1-4e2d-b965-55b99c36cee8', '2023-10-16 17:00:00.000000', false, '2023-10-16 16:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (53, '41d18c1b-f4e0-4437-ac61-e290d8dc797b', '2023-10-16 17:30:00.000000', false, '2023-10-16 17:00:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (54, '5bba4f41-53bb-4eaa-9081-d5b3247ea922', '2023-10-16 18:00:00.000000', false, '2023-10-16 17:30:00.000000', 3, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (8, 'b7dbb221-818e-463f-98b1-896499b8ab28', '2023-10-16 13:00:00.000000', false, '2023-10-16 12:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (2, 'ba49050f-4aeb-48fd-a692-4b0d362bd306', '2023-10-16 10:00:00.000000', false, '2023-10-16 09:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (1, '99f82fd6-46b3-465a-9dd3-f1f0dfc0db11', '2023-10-16 09:30:00.000000', false, '2023-10-16 09:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (15, '357aa748-da47-4a3c-b8fa-a3aec419cf6b', '2023-10-16 16:30:00.000000', true, '2023-10-16 16:00:00.000000', 1, 21);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (3, 'a07c3005-11f0-44d0-9b6d-12ba435caf04', '2023-10-16 10:30:00.000000', false, '2023-10-16 10:00:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (6, '045b6b02-3366-44fd-8243-3de233841028', '2023-10-16 12:00:00.000000', false, '2023-10-16 11:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (10, '3334f2ed-8141-451d-a76c-66deb8ad9fee', '2023-10-16 14:00:00.000000', false, '2023-10-16 13:30:00.000000', 1, null);
INSERT INTO public.time_slot (id, uuid, finish_time, occupied, start_time, cabinet_id, ticket_id) VALUES (16, 'f216939e-269a-49ed-ba30-2f494c469f0b', '2023-10-16 17:00:00.000000', true, '2023-10-16 16:30:00.000000', 1, 23);
