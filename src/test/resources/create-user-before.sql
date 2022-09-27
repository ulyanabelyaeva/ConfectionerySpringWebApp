delete from roles;
delete from users;
delete from user_roles;
delete from cart;

insert into roles (id, name) values (10, "USER");
insert into users (id, first_name, password, phone)
values (10, "Мария", "$2a$12$ZAR.rLzuFGQpQBuB1BBEG.SPeqao7qV7KRbvRIPXAdbd21p1rBL1e", "89185555555");
insert into user_roles (user_id, roles_id) values (10, 10);
insert into cart (status, user_id, cost) values (false, 10, 0);
