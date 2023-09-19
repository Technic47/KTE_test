create table cabinet_timeslots
(
    cabinet_id   bigint not null
        constraint fk4gvg9el6ywk304eq7tdvoop3v
            references cabinet,
    timeslots_id bigint not null
        constraint uk_8m59qmcpiqxhimaj1mrrd8grq
            unique
        constraint fkptukwsuvovp50w4i1u1dmgb5n
            references time_slot,
    primary key (cabinet_id, timeslots_id)
)
    using ???;

alter table cabinet_timeslots
    owner to postgres;

INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 11);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 14);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 1);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 4);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 3);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 6);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 16);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 12);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 18);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 7);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 9);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 15);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 2);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 5);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 8);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 17);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 13);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 20);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 24);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 23);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 30);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 35);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 31);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 32);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 33);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 29);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 25);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 21);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 28);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 27);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 34);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 19);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 36);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 26);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 22);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 48);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 45);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 49);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 51);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 44);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 53);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 54);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 47);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 52);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 39);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 46);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 43);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 50);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 38);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 42);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 40);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 37);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (3, 41);
