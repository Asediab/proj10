INSERT INTO public.reservation (id, date_creation, date_expiration, document_id, is_active, is_mail_sent, is_taken_by_user, mail_expiration_date, mail_sent_date, user_id, user_name, document_name, user_surname)
VALUES (2, '2020-07-15', null, 1, true, false, false, null, null, 1, 'User', 'À la recherche du temps perdu', 'User');
INSERT INTO public.reservation (id, date_creation, date_expiration, document_id, is_active, is_mail_sent, is_taken_by_user, mail_expiration_date, mail_sent_date, user_id, user_name, document_name, user_surname)
VALUES (3, '2020-07-15', null, 2, true, false, false, null, null, 1, 'User', 'Les Plaisirs et les Jours', 'User');

SELECT pg_catalog.setval('reservation_id_seq', 3, true);