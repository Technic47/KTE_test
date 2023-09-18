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

create unique index cabinet_timeslots_pkey
    on cabinet_timeslots using ??? (cabinet_id, timeslots_id);

create unique index uk_8m59qmcpiqxhimaj1mrrd8grq
    on cabinet_timeslots using ??? (timeslots_id);

INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10911);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10907);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10908);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10916);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10917);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10905);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10904);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10913);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10914);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10919);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10912);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10910);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10915);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10918);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10903);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10906);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10902);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (1, 10909);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10922);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10926);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10923);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10921);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10927);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10931);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10930);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10924);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10934);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10920);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10937);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10928);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10935);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10929);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10932);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10936);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10933);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (2, 10925);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10943);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10946);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10949);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10954);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10947);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10953);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10939);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10941);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10950);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10945);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10948);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10944);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10955);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10942);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10940);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10952);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10951);
INSERT INTO public.cabinet_timeslots (cabinet_id, timeslots_id) VALUES (4, 10938);
