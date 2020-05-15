create table usr
(
    id       bigserial not null
        constraint usr_pkey
            primary key,
    email    varchar(255)
        constraint uk_g9l96r670qkidthshajdtxrqf
            unique,
    name     varchar(255),
    password varchar(255),
    surname  varchar(255)
);

alter table usr
    owner to loan;

create table user_role
(
    user_id bigint not null
        constraint fkfpm8swft53ulq2hl11yplpr5
            references usr,
    roles   varchar(255)
);

alter table user_role
    owner to loan;

