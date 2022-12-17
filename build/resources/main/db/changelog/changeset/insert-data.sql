-- changeset nikitasudaev: 3
insert into discount_card (number, percent) values (1234, 10);
insert into discount_card (number, percent) values (5678, 20);
insert into discount_card (number, percent) values (9003, 30);

insert into product(name, price, discount) values ('pencil', 0.24, false);
insert into product(name, price, discount) values ('pen', 0.35, false);
insert into product(name, price, discount) values ('ruler', 0.32, true);
insert into product(name, price, discount) values ('pencil case', 0.85, false);
insert into product(name, price, discount) values ('backpack', 2.43, true);