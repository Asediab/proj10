create table loan
(
    id                  int8    not null,
    copy_of_document_id int8    not null,
    document_id         int8    not null,
    date_creation       date    not null,
    date_expiration     date    not null,
    number_of_renewals  int4    not null,
    user_id             int8    not null,
    returned            boolean not null,
    primary key (id)
);

create sequence loan_id_seq;

alter table loan
    alter column id set default nextval('public.loan_id_seq');

alter sequence loan_id_seq owned by loan.id;

