INSERT INTO public.type_of_document (id, type)
VALUES (1, 'Book');
INSERT INTO public.type_of_document (id, type)
VALUES (2, 'Magazine');
INSERT INTO public.type_of_document (id, type)
VALUES (3, 'Newspaper');


INSERT INTO public.library (id, name, address, description, telephone)
VALUES (1, 'Bibliothèque Mazarine', '23 Quai de Conti, Paris, Île-de-France, 75006, France',
        'With a grandiose façade and luminous reading room, it’s hard to believe that the Bibliotheque Mazarine is often overlooked by tourists making their way across the Seine and into the St-Germain-des-Prés. The oldest public library in France, it has a modern collection focusing on French history from the 12th–17th centuries, as well as thousands of rare, medieval manuscripts.',
        '+33144414406');


INSERT INTO public.document (id, author, copy_available, number_of_pages, titre, year_os_issue, type_of_document_id,
                             photo)
VALUES (1, 'Marcel Proust', 4, 350, 'À la recherche du temps perdu', '1995-04-25', 1, 'tempperdu');
INSERT INTO public.document (id, author, copy_available, number_of_pages, titre, year_os_issue, type_of_document_id,
                             photo)
VALUES (2, 'Marcel Proust', 3, 180, 'Les Plaisirs et les Jours', '2000-04-25', 1, 'plaisirs_les_jours');
INSERT INTO public.document (id, author, copy_available, number_of_pages, titre, year_os_issue, type_of_document_id,
                             photo)
VALUES (3, 'Marcel Proust', 4, 320, 'Jean Santeuil', '1995-10-20', 1, 'jean_santeuil');


INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (1, 65412398745, 1, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (2, 78691489672, 1, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (3, 77858533398, 1, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (4, 12278272782, 1, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (5, 22937937517, 2, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (6, 17283737582, 2, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (7, 44267895257, 2, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (8, 82772785767, 3, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (9, 74125628965, 3, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (10, 44588833993, 3, 1);
INSERT INTO public.copy_of_document (id, serial_number, document_id, library_id)
VALUES (11, 41255996474, 3, 1);

