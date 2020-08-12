insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (2, 2, 1, '2020-08-01', '2020-09-01', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (5, 5, 2, '2020-08-21', '2020-09-27', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (1, 1, 1, '2020-08-01', '2020-09-01', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (4, 4, 1, '2020-08-02', '2020-09-02', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (3, 3, 1, '2020-08-01', '2020-09-01', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (7, 7, 2, '2020-08-24', '2020-09-28', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (9, 9, 3, '2020-08-30', '2020-09-30', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (8, 8, 3, '2020-08-15', '2020-09-15', 0, 2, 'false');
insert into public.loan (id, copy_of_document_id, document_id, date_creation, date_expiration, number_of_renewals,
                         user_id, returned)
values (6, 6, 2, '2020-08-01', '2020-09-01', 0, 2, 'false');


SELECT pg_catalog.setval('loan_id_seq', 9, true);
