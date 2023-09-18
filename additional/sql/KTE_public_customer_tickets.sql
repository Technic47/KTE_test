create table customer_tickets
(
    customer_id bigint not null
        constraint fkgmccu5g6iwcs5jsno629op6hv
            references customer,
    tickets_id  bigint not null
        constraint uk_eam6my5rg3njhj27u4hfjjnod
            unique
        constraint fkhnt21eyjy20dkplg8hrr56m8u
            references ticket,
    primary key (customer_id, tickets_id)
)
    using ???;

alter table customer_tickets
    owner to postgres;

create unique index customer_tickets_pkey
    on customer_tickets using ??? (customer_id, tickets_id);

create unique index uk_eam6my5rg3njhj27u4hfjjnod
    on customer_tickets using ??? (tickets_id);

