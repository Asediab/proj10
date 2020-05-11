INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (1, 1, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (2, 2, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (3, 3, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (4, 4, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (5, 5, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (6, 6, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (7, 7, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (8, 8, '2020-04-24', '2020-05-08', 0, 3, false);
INSERT INTO public.loan (id, copy_of_document_id, date_creation, date_expiration, number_of_renewals, user_id,
                         returned)
VALUES (9, 9, '2020-04-24', '2020-05-08', 0, 3, false);

SELECT pg_catalog.setval('loan_id_seq', 9, true);
