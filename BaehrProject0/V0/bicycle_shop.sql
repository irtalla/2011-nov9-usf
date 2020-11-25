-- clearing all data
drop table roles cascade;
drop table users cascade;
drop table bicycle_types cascade;
drop table bicycles cascade;
drop table offers cascade;
drop table purchases cascade;

-- DDL
create table roles(
	role_id int primary key,
	role_name varchar(30) not null
);

create table users(
	user_id serial primary key,
	username varchar(30) unique not null,
	password varchar(30) not null,
	role_id int not null references roles,
	account_balance numeric default 0
);

create table bicycle_types(
	type_id serial primary key,
	manufacturer varchar(30) not null,
	model varchar(30) not null
);

create table bicycles(
	bicycle_id serial primary key,
	type_id int references bicycle_types,
	year int,
	price numeric not null check (price > 0),
	owner_id int not null references users,
	status varchar(30)
);

create table offers(
	offer_id serial primary key,
	bicycle_id int references bicycles,
	user_id int references users,
	offer numeric not null check (offer > 0),
	status varchar(30)
);

create table purchases(
	purchase_id serial primary key,
	bicycle_id int references bicycles,
	user_id int references users,
	price numeric not null check (price > 0),
	outstanding_balance numeric not null default 0,
	payments_remaining int not null default 0
);
	

-- Populating the database

insert into roles (role_id, role_name) values
	(0, 'manager'),
	(1, 'employee'),
	(2, 'customer');

select * from roles;

insert into users values
	(default, 'store', 'owner', 0, default),
	(default, 'user1', 'pass1', 1, default),
	(default, 'user2', 'pass2', 1, default),
	(default, 'user3', 'pass3', 2, default),
	(default, 'user4', 'pass4', 2, default),
	(default, 'user5', 'pass5', 2, default),
	(default, 'user6', 'pass6', 2, default),
	(default, 'user7', 'pass7', 2, default),
	(default, 'user8', 'pass8', 2, default);

select * from users;

insert into bicycle_types values
	(default, 'Schwinn', 'Mesa 1'),
	(default, 'Schwinn', 'Mesa 3'),
	(default, 'Schwinn', 'High Timber'),
	(default, 'Schwinn', 'Fastback Carbon'),
	(default, 'Polygon', 'Cascade 1'),
	(default, 'Polygon', 'Cascade 2'),
	(default, 'Polygon', 'Cascade 3'),
	(default, 'Polygon', 'Cascade 4'),
	(default, 'Diamondback', 'Release 1'),
	(default, 'Diamondback', 'Release 2');

select * from bicycle_types;

-- delete from bicycles; 

insert into bicycles values
	(default, 1, 2018, 649.99, 1, 'Available'),
	(default, 1, 2018, 450, 7, 'Sold'),
	(default, 2, 2020, 459.99, 1, 'Available'),
	(default, 2, 2019, 459.99, 6, 'Sold'),
	(default, 3, 2019, 369.99, 1, 'Available'),
	(default, 3, 2019, 369.99, 1, 'Available'),
	(default, 3, 2019, 369.99, 1, 'Available'),
	(default, 3, 2019, 369.99, 1, 'Available'),
	(default, 4, 2019, 1999.99, 1, 'Available'),
	(default, 5, 2020, 499.99, 1, 'Available'),
	(default, 5, 2020, 499.99, 6, 'Sold'),
	(default, 6, 2020, 449.99, 4, 'Sold'),
	(default, 6, 2020, 499.99, 1, 'Available'),
	(default, 6, 2020, 499.99, 1, 'Available'),
	(default, 7, 2020, 499.99, 1, 'Available'),
	(default, 7, 2020, 499.99, 1, 'Available'),
	(default, 8, 2020, 2500, 5, 'Sold'),
	(default, 8, 2020, 2500, 1, 'Available'),
	(default, 8, 2020, 2500, 1, 'Available'),
	(default, 9, 2020, 3250, 1, 'Available');

select * from bicycles
order by bicycle_id;

insert into offers values
	(default, 2, 3, 450, 'Accepted'),
	(default, 6, 4, 300, 'Rejected'),
	(default, 20, 3, 2850, 'Pending'),
	(default, 20, 4, 2850, 'Pending'),
	(default, 20, 5, 2850, 'Pending');

select * from offers;

insert into purchases values
	(default, 2, 7, 250, 0, 0),
	(default, 4, 6, 459.99, 100, 2),
	(default, 11, 6, 459.99, 300, 5),
	(default, 12, 4, 449.99, 0, 0),
	(default, 17, 5, 2500, 0, 0);

select * from purchases;


select * from bicycles as b join bicycle_types as bt on b.type_id = bt.type_id;


select O.offer_id, O.bicycle_id, bicycle_types.type_id, O.offer, O.user_id, O.status
from offers O
join bicycles on O.bicycle_id = offers.bicycle_id 
join bicycle_types on bicycles.type_id = bicycle_types.type_id 
where O.bicycle_id = 6;

select o.offer_id,  o.offer, o.user_id, o.status, o.bicycle_id, b.Price, b.status, b.year, b.owner_id, b.type_id, bt.manufacturer, bt.model 
from offers o
join bicycles b on o.bicycle_id = b.bicycle_id 
join bicycle_types bt on bt.type_id = b.type_id
where b.bicycle_id = 6;

select o.offer_id, o.user_id, o.status, o.offer, o.bicycle_id, b.price, b.status, b.year, b.owner_id, b.type_id, bt.manufacturer, bt.model 
from offers o
join bicycles b on b.bicycle_id = o.bicycle_id
join bicycle_types bt on bt.type_id = b.type_id;

select p.purchase_id, p.bicycle_id, p.user_id, p.price, p.outstanding_balance, p.payments_remaining, b.status, b."year", b.type_id, bt.manufacturer, bt.model
from purchases p
join bicycles b on p.bicycle_id = b.bicycle_id
join bicycle_types bt on b.type_id = bt.type_id;

select * from bicycles  join bicycle_types  on bicycles.type_id = bicycle_types.type_id  where bicycles.bicycle_id = 1;

delete from users where username = 'test';

select * from offers order by bicycle_id;

update offers
set status = 'Pending'
where bicycle_id = 20 and offer_id != 3;