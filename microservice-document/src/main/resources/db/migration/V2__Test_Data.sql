INSERT INTO public.type_of_document (id, type)
VALUES (1, 'Book');
INSERT INTO public.type_of_document (id, type)
VALUES (2, 'Magazine');
INSERT INTO public.type_of_document (id, type)
VALUES (3, 'Newspaper');

SELECT pg_catalog.setval('type_of_document_id_seq', 4, true);




INSERT INTO public.library (id, name, address, description, telephone)
VALUES (1, 'Bibliothèque Mazarine', '23 Quai de Conti, Paris, Île-de-France, 75006, France',
        'With a grandiose façade and luminous reading room, it’s hard to believe that the Bibliotheque Mazarine is often overlooked by tourists making their way across the Seine and into the St-Germain-des-Prés. The oldest public library in France, it has a modern collection focusing on French history from the 12th–17th centuries, as well as thousands of rare, medieval manuscripts.',
        '+33144414406');

SELECT pg_catalog.setval('library_id_seq', 2, true);



INSERT INTO public.document (id, author, copy_available, copy_total, reservations, number_of_pages, titre, year_os_issue, type_of_document_id,
                             photo, description)
VALUES (1, 'Marcel Proust', 0, 4, 1, 350, 'À la recherche du temps perdu', '1995-04-25', 1, 'tempperdu',
        'À la recherche du temps perdu, couramment évoqué plus simplement sous le titre La Recherche, est un roman de Marcel Proust, écrit de 1906 à 1922 et publié de 1913 à 1927 en sept tomes, dont les trois derniers parurent après la mort de lauteur');
INSERT INTO public.document (id, author, copy_available, copy_total, reservations, number_of_pages, titre, year_os_issue, type_of_document_id,
                             photo, description)
VALUES (2, 'Marcel Proust', 0, 3, 1, 180, 'Les Plaisirs et les Jours', '2000-04-25', 1, 'plaisirs_les_jours',
        'Les Plaisirs et les Jours is a collection of prose poems and novellas by Marcel Proust. It was first published in 1896 by Calmann-Lévy, and was Proust''s first publication.');
INSERT INTO public.document (id, author, copy_available, copy_total, reservations, number_of_pages, titre, year_os_issue, type_of_document_id,
                             photo, description)
VALUES (3, 'Marcel Proust', 2, 4, 0, 320, 'Jean Santeuil', '1995-10-20', 1, 'jean_santeuil',
        'Jean Santeuil is an unfinished novel written by Marcel Proust. It was written between 1896 and 1900, and published after the author''s death. The first French edition was published in 1952 by Gallimard.');

SELECT pg_catalog.setval('document_id_seq', 4, true);




INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (1, 65412398745, 1, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (2, 78691489672, 1, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (3, 77858533398, 1, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (4, 12278272782, 1, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (5, 22937937517, 2, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (6, 17283737582, 2, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (7, 44267895257, 2, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (8, 82772785767, 3, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (9, 74125628965, 3, 1, false);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (10, 44588833993, 3, 1, true);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id, available)
VALUES (11, 41255996474, 3, 1, true);

SELECT pg_catalog.setval('copy_of_document_id_seq', 12, true);
