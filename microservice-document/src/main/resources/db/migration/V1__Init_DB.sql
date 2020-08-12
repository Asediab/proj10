create table copy_of_document
(
    id            bigserial not null,
    serial_number int8      not null,
    document_id   int8      not null,
    library_id    int8      not null,
    available     boolean   not null,
    primary key (id)
);

create table document
(
    id                  bigserial     not null,
    author              varchar(255)  not null,
    copy_available      int4          not null,
    number_of_pages     int4          not null,
    titre               varchar(255)  not null,
    year_os_issue       date          not null,
    type_of_document_id int8          not null,
    photo               varchar(255)  not null,
    description         varchar(2000) not null,
    primary key (id)
);

create table library
(
    id          bigserial     not null,
    name        varchar(50)   not null,
    address     varchar(255)  not null,
    description varchar(1000) not null,
    telephone   varchar(255)  not null,
    primary key (id)
);

create table type_of_document
(
    id   bigserial    not null,
    type varchar(255) not null,
    primary key (id)
);

alter table copy_of_document
    add constraint FK80ddnr8mkirotu6c0mkn3s5to foreign key (document_id) references document;

alter table copy_of_document
    add constraint FKcw0wlm0ruy5h0oiv8kys7l9vf foreign key (library_id) references library;

alter table document
    add constraint FK7tmyny3usukfv63p1van2pdp5 foreign key (type_of_document_id) references type_of_document;
