
create schema shopapp;  

/* DDL Statements - define the relations in the schema */

create table user_role (
	id serial primary key,
	name varchar(10) unique not null
);

create table person (
	id serial primary key,
	username varchar(30) unique not null,
	passwd varchar(30) not null,
	user_role_id integer references user_role
);

create table category (
	id serial primary key,
	name varchar(40) unique not null
);

create table status (
	id serial primary key,
	name varchar(40) unique not null
);

create table product (
	id serial primary key,
	name varchar(30),
	price numeric not null,
	status_id integer references status,
	category_id integer references category
);


create table offer (
	id serial primary key, 
	customer_id integer references person,
	product_id integer references product, 
	status_id integer references status, 
	amount numeric
); 


create table feature (
	id serial primary key,
	name varchar(40) unique not null
);


-- join tables --
create table purchase (
	person_id integer references person,
	product_id integer references product
);


create table product_feature (
	product_id integer references product,
	feature_id integer references feature
);


/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/

/* DML - initalize reletions with tuples */ 

insert into user_role (id, name) values 
	(default, 'customer'),
	(default, 'employee'),
	(default, 'manager'); 

insert into person (id, username, passwd, user_role_id) values
	(default, 'Travis', '90210', 1),
	(default, 'John', '12345', 1),
	(default, 'Kara', 'working', 1),
	(default, 'Sam', 'happy', 2),
	(default, 'Ariel', 'money', 3);


insert into category (id, name) values 
	(default, 'Unspecified'),
	(default, 'Bike'),
	(default, 'Helmet'),
	(default, 'Pegs'),
	(default, 'Chain');


insert into status (id, name) values 
	(default, 'available'),
	(default, 'unavailable'),
	(default, 'pending'),
	(default, 'accepted'),
	(default, 'rejected'); 

insert into product (id, name, price, status_id, category_id) values
	(default, 'Jumper', 1500.00, 1, 2),
	(default, 'Zipper', 350.00, 1, 2),
	(default, 'Dynomobile', 299.99, 1, 2),
	(default, 'Echo', 375.00, 1, 2),
	(default, 'Mazeocraft', 400.00, 1, 2),
	(default, 'Miracle', 2999.99, 1, 2),
	(default, 'Zyphr', 999.99, 1, 2),
	(default, 'Ergo', 449.99, 1, 2),
	(default, 'GrizzlE', 675.00, 1, 2),
	(default, 'Turbo', 400.00, 1, 2),
	(default, 'Cruiser', 299.99, 1, 2),
	(default, 'Dauntless', 100.00, 1, 3),
	(default, 'HeadStrong', 49.99, 1, 3),
	(default, 'Clutch', 65.00, 1, 3);


insert into feature (id, name) values
	(default, 'insurance plan'),
	(default, 'spare parts'); 

insert into product_feature (product_id, feature_id) values
	(4, 1),
	(5, 2),
	(6, 1),
	(6, 2), 
	(7, 1),
	(7, 2), 
	(5, 1),
	(8, 1); 



insert into offer (id, customer_id, product_id, status_id, amount) values 
	(default, 1, 5, 3, 300.00),
	(default, 1, 12, 3, 1300.00),
	(default, 2, 7, 3, 100.00),
	(default, 1, 3, 3, 370.00); 

insert into offer (id, customer_id, product_id, status_id, amount) values 
	(default, 3, 7, 3, 1300.00),
	(default, 2, 7, 3, 1500.00),
	(default, 4, 7, 3, 1100.00); 

insert into feature (id, name) values 
	(default, 'night light');

insert into purchase values 
	(3, 12); 

truncate table purchase; 

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/

/* DQL - test DML statements */

select * from user_role; 

select * from person; 

select * from category; 

select * from status; 

select * from product;

select * from offer; 

select * from purchase; 

select product.id, product.name, product.price, category.name 
from product
join category
on product.category_id = category.id; 

select * from feature; 

select product.name, subquery.f_name
	from product
	join 
		( select product_feature.name as f_name, product_product_feature.* 
			from product_product_feature
			join product_feature 
			on product_product_feature.product_feature_id = product_feature.id
		) as subquery
	on subquery.product_id = product.id; 


/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/

/* DQL - DML statements for JDBC prepared statements  */


select 
	subqry.product_id as product_id,
	subqry.product_name as product_name,
	subqry.product_price as product_price, 
	subqry.status_id as status_id,
	subqry.status_name as status_name,
	category.id as category_id,
	category.name as category_name 
	from category 
		join (
			select 
				product.id as product_id,
				product.name as product_name,
				product.price as product_price,
				product.status_id as status_id, 
				status.name as status_name,
				product.category_id as category_id
				from product 
				join status 
				on product.status_id = status.id
			) as subqry
	on subqry.category_id = category.id; 



select feature.id as feature_id, feature.name as feature_name 
from product_feature
join feature
on product_feature.feature_id = feature.id
where product_feature.product_id = 1; 


select * from product; 

select
	offer.id as offer_id, 
 	offer.customer_id as customer_id, 
 	offer.product_id as product_id, 
 	offer.amount as offer_amount,
 	offer.status_id as status_id,
 	status.name as status_name
	from offer
		join status 
		on offer.status_id = status.id; 


select  
	product_feature.feature_id as feature_id, 
	feature.name as feature_name
	from product_feature
		join feature 
		on product_feature.feature_id = feature.id
	where product_feature.product_id = 6; 


select 
	person.id as person_id,
	person.username as person_username,
	person.passwd as person_password,
	person.user_role_id as role_id,
	user_role.name as role_name 
	from person
		join user_role
		on person.user_role_id = user_role.id;
	

select 
purchase.person_id as person_id, 
purchase.product_id as product_id
from purchase; 

drop table offer; 

 select 
	person.id as person_id, 
	person.username as person_username, 
	person.passwd as person_password, 
	person.user_role_id as role_id, 
	user_role.name as role_name 
	from person 
		join user_role 
		on person.user_role_id = user_role.id 
	where person.username like 'Travis';

 select 
	product_feature.feature_id as feature_id, 
	feature.name as feature_name 
	from product_feature 
		join feature 
		on product_feature.feature_id = feature.id 
	where product_feature.product_id = 1; 




/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/

/* DQL - DML statements for JDBC prepared statements  */

drop procedure finalize_purchase; 

create or replace procedure finalize_purchase(person_id_param int, product_id_param int) as $$
declare 
	current_status int;
	unavailable_status int;
	rejected_status int; 
	accepted_status int; 
	product_name varchar(30); 
begin
	-- get status of product about to be purchased 
	select status_id into current_status from product where id = product_id_param;
	-- get id of avaliable status tuple
	select id into unavailable_status from status where name = 'unavailable';
	select id into rejected_status from status where name = 'rejected';
	select id into accepted_status from status where name = 'accepted'; 


	if current_status <> unavailable_status then
	
		-- if the product is avaliable, set its status to unavailable, reject
		-- all offers not from the person, and accept all offers from person
		insert into purchase values (person_id_param, product_id_param);
		update product set status_id = unavailable_status where id = product_id_param;
		update offer set status_id = accepted_status
			where 
				offer.customer_id = person_id_param and 
				offer.product_id = product_id_param;
		update offer set status_id = rejected_status 
			where 
				offer.customer_id != person_id_param and 
				offer.product_id = product_id_param;
			
	else
		select name into product_name from product where id = product_id_param;
		raise exception 'Product with name [ % ] and id [ % ] is not available', product_name, product_id_param;
	end if;
end;
$$ language plpgsql;



create or replace procedure delete_product(product_id_param int) as $$
declare 
	current_status int;
	available_status int;
	product_name varchar(30);
begin
	select status_id into current_status from product where id = product_id_param;
	select id into available_status from status where name = 'available'; 

	-- only delete available products 
	if current_status = available_status then	
		-- remove foregin keys that reference product 
		delete from offer where product_id = product_id_param; 
		delete from product_feature where product_id = product_id_param; 
		
		-- finally, delete product 
		delete from product where id = product_id_param;
	else 
		select name into product_name from product where id = product_id_param;
		raise exception 'Product with name [ % ] and id [ % ] has already been purchased', product_name, product_id_param;
	end if; 
end
$$ language plpgsql;

call delete_product(3); 

