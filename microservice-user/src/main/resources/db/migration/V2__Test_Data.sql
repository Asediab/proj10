insert into public.usr (id, email, name, password, surname) values (2, 'user2@user.com', 'User2', '$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q', 'User2');
insert into public.usr (id, email, name, password, surname) values (1, 'user@user.com', 'User', '$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q', 'User');
insert into public.usr (id, email, name, password, surname) values (3, 'admin@admin.com', 'Admin', '$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q', 'Admin');

SELECT pg_catalog.setval('usr_id_seq', 4, true);



insert into public.user_role (user_id, roles) values (1, 'USER');
insert into public.user_role (user_id, roles) values (2, 'USER');
insert into public.user_role (user_id, roles) values (3, 'ADMIN');


