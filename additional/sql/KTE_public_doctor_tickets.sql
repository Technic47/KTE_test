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

create unique index doctor_tickets_pkey
    on doctor_tickets using ??? (doctor_id, tickets_id);

create unique index uk_k29hj9hdti9dearttrpsvrjob
    on doctor_tickets using ??? (tickets_id);

