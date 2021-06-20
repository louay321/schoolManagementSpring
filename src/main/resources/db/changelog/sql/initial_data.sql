INSERT INTO Student(id , first_name, last_name, address, created) VALUES (1, 'si', 'zebi', 'el mourouj 2, Pecs', now());

INSERT INTO Teacher(id , first_name, last_name, address, created) VALUES (1, 'prof', 'mnayek', 'hayzouhour, Pecs', now());

INSERT INTO Subject(id, credits, created) VALUES (1, 5, now());

insert into users(id, name, enabled, password, version, created)
values(1, 'admin', true, 'admin', 0, now());

insert into roles(id, name, version, created)
values (1, 'admins', 0, now());

insert into user_roles(id, user_id, role_id, version, created)
values (1,
        (select id from users where name='admin'),
        (select id from roles where name='admins'),
        0,
        now());
