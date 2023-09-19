create table doctor_tickets
(
    doctor_id  bigint not null
        constraint fk5ksgjs7tekhhw1y6uy1n5s3a9
            references doctor,
    tickets_id bigint not null
        constraint uk_k29hj9hdti9dearttrpsvrjob
            unique
        constraint fkq4loiyo07j6uhukxa4gb7fjya
            references ticket,
    primary key (doctor_id, tickets_id)
)
    using ???;

alter table doctor_tickets
    owner to postgres;

INSERT INTO public.doctor_tickets (doctor_id, tickets_id) VALUES (4, 21);
INSERT INTO public.doctor_tickets (doctor_id, tickets_id) VALUES (4, 23);
