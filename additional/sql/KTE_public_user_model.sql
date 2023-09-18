create table user_model
(
    id       bigint not null
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

create unique index user_model_pkey
    on user_model using ??? (id);

create unique index uk_la8xty622mpbfdhq2iixt9lhu
    on user_model using ??? (email);

create unique index uk_asi811mgonyf7p7aj2tl97a91
    on user_model using ??? (username);

INSERT INTO public.user_model (id, uuid, active, created, email, enabled, password, updated, username) VALUES (202, '74264fc9-ecfe-49d8-84d6-b9c8b7dad7fc', true, '2023-09-18 11:37:48.440000', null, false, '$2a$10$1AXnxPNc7uzzQaziwD2yce2HqymZCvIxvHs1DRxKXYIDn1E0OEBCq', null, 'pavel');
