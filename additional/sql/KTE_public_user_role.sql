create table user_role
(
    user_id bigint not null
        constraint fkryojaj4bpgbcrlxbtdbejvwn
            references user_model,
    status  varchar(255)
        constraint user_role_status_check
            check ((status)::text = ANY
                   ((ARRAY ['ROLE_USER'::character varying, 'ROLE_ADMIN'::character varying])::text[]))
)
    using ???;

alter table user_role
    owner to postgres;

INSERT INTO public.user_role (user_id, status) VALUES (202, 'ROLE_ADMIN');
