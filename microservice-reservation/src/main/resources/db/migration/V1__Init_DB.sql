create table reservation
(
    id                   bigserial    not null
        constraint reservation_pkey
            primary key,
    date_creation        date,
    date_expiration      date,
    document_id          bigint       not null,
    is_active            boolean      not null,
    is_mail_sent         boolean      not null,
    is_taken_by_user     boolean      not null,
    mail_expiration_date date,
    mail_sent_date       date,
    user_id              bigint       not null,
    user_name            varchar(255) not null,
    user_email           varchar(255) not null,
    document_name        varchar(500) not null,
    user_surname         varchar(255) not null
);

alter table reservation
    owner to loan;