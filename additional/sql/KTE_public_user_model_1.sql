create table user_model
(
    id       bigserial
        primary key,
    uuid     uuid,
    active   boolean,
    created  timestamp(6),
    email    varchar(255)
        constraint uk_la8xty622mpbfdhq2iixt9lhu
            unique,
    enabled  boolean,
    password varchar(1000),
    updated  timestamp(6),
    username varchar(255)
        constraint uk_asi811mgonyf7p7aj2tl97a91
            unique
)
    using ???;

alter table user_model
    owner to postgres;

INSERT INTO public.user_model (id, uuid, active, created, email, enabled, password, updated, username) VALUES (1, 'd12de86b-4a56-4a59-9fe8-162ccdbe326a', true, '2023-09-19 22:06:58.790000', null, false, '$2a$10$dGzFqzhFM/WQ1syX5IohUOpHk6hVSOruYKFez1P0BOxIlcEUDYo6i', null, 'pavel');
