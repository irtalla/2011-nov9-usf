/*
 * insert into breed(id, name) values (default, 'tabby');
insert into breed(id, name) values (default, 'calico');
insert into breed(id, name) values (default, 'siamese');
insert into breed(id, name) values (default, 'persian');

insert into status(id, name) values (default, 'adopted');
insert into status(id, name) values (default, 'available');

insert into cat(id, name, age, status_id, breed_id) values (default, 'howard', 3, 1, 1);
insert into cat(id, name, age, status_id, breed_id) values (default, 'howard1', 4, 3, 3);
insert into cat(id, name, age, status_id, breed_id) values (default, 'howard2', 3, 3, 1);
insert into cat(id, name, age, status_id, breed_id) values (default, 'howard3', 5, 1, 3);
insert into cat(id, name, age, status_id, breed_id) values (default, 'howard4', 3, 3, 4);
insert into cat(id, name, age, status_id, breed_id) values (default, 'howard5', 7, 1, 2);



					select * from 
					(select cat_status.id, cat_status.name, age, status_id, status_name, breed_id, 
					breed.name as breed_name from
					(select cat.id, cat.name, age, status_id, breed_id, status.name as status_name from
					cat join status on status_id = status.id) as cat_status
					join breed on breed_id = breed.id) as foo where status_id = 2;
					
				commit

insert into user_role(id, name) values (default, 1);
insert into user_role(id, name) values (default, 2);

insert into person(id, username, passwd, user_role_id) values (default, 'person1', 'pass1', 1);
insert into person(id, username, passwd, user_role_id) values (default, 'person2', 'pass2', 2);
insert into person(id, username, passwd, user_role_id) values (default, 'person3', 'pass3', 2);
insert into person(id, username, passwd, user_role_id) values (default, 'person4', 'pass4', 1);
insert into person(id, username, passwd, user_role_id) values (default, 'person5', 'pass5', 2);
insert into person(id, username, passwd, user_role_id) values (default, 'person6', 'pass6', 1);

insert into person_cat(person_id, cat_id) values (1, 10);
insert into person_cat(person_id, cat_id) values (1, 9);
insert into person_cat(person_id, cat_id) values (1, 8);
insert into person_cat(person_id, cat_id) values (2, 7);
insert into person_cat(person_id, cat_id) values (3, 6);
insert into person_cat(person_id, cat_id) values (4, 5);
insert into person_cat(person_id, cat_id) values (5, 4);
insert into person_cat(person_id, cat_id) values (5, 3);
insert into person_cat(person_id, cat_id) values (5, 2);
insert into person_cat(person_id, cat_id) values (6, 1);

				select cat_status.id, cat_status.name, age, status_id, status_name, breed_id, 
					breed.name as breed_name from
					(select cat.id, cat.name, age, status_id, breed_id, status.name as status_name from
					cat join status on status_id = status.id) as cat_status
					join breed on breed_id = breed.id;
	
	*SELECT h.Address, p.Name, p.Address as OwnerAddress, i.url FROM house AS h
INNER JOIN person AS p ON p.personID = h.personID
INNER JOIN images AS i ON i.personID = p.personID
GROUP BY h.houseID
	**/	
--commit;
--select * from (select * from person 
--inner join person_cat on person.id = person_cat.person_id
--inner join cat on person_cat.cat_id = cat.id group by cat.id, person.id, person_cat.person_id, person_cat.cat_id) as allPeople
--where allPeople.person_id = 1 group by allPeople.

