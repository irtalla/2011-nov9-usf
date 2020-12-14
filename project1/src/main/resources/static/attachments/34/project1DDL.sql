--create table approval_doc_type (
--	id serial primary key,
--	name varchar(15) unique not null
--);
--
--insert into approval_doc_type(id, name) values (default, 'presentation');
--insert into approval_doc_type(id, name) values (default, 'grades');
--insert into approval_doc_type(id, name) values (default, 'rfc');
--
--create table reimbursement_status(
--	id serial primary key,
--	name varchar(15) unique not null
--);
--
--insert into reimbursement_status(id, name) values (default, 'pending');
--insert into reimbursement_status(id, name) values (default, 'awarded');

--create table individual_role (
--	id serial primary key,
--	name varchar(15) unique not null
--);
--
--insert into individual_role (id, name) values(default, 'supervisor');
--insert into individual_role (id, name) values(default, 'employee');
--insert into individual_role (id, name) values(default, 'benco');
--
--create table event_type (
--	id serial primary key,
--	name varchar(15) unique not null
--);
--
--insert into event_type (id, name) values(default, 'university');
--insert into event_type (id, name) values(default, 'seminar');
--insert into event_type (id, name) values(default, 'cert prep');
--insert into event_type (id, name) values(default, 'certification');
--insert into event_type (id, name) values(default, 'tech training');

--create table event (
--	id serial primary key,
--	type integer references event_type
--);

--create table application_doc (
--	id serial primary key,
--	file_name varchar(15) unique not null,
--	event integer references event,
--	blob bytea
--);

--create table department(
--	id serial primary key,
--	name varchar(15)
--);
--
--create table individual(
--	id serial primary key,
--	role integer references individual_role,
--	department integer references department
--);

--ALTER TABLE department
--ADD COLUMN head integer references individual;


--create table reimbursement (
--	id serial primary key,
--	status integer references reimbursement_status,
--	event integer references event,
--	employee integer references individual,
--	amount real
--);

--create table approval_doc(
--	id serial primary key,
--	reimbursement integer references reimbursement,
--	file_name varchar(15) unique not null,
--	blob bytea,
--	type integer references approval_doc_type
--);

--ALTER TABLE individual
--ADD COLUMN name varchar(15),
--add column passwd varchar(25);

--create table approvals(
--id serial primary key,
--reimbursement_id integer references reimbursement,
--benco boolean,
--department_head boolean,
--supervisor boolean
----benco integer references individual,
----department_head integer references individual,
----supervisor integer references individual
--);

--ALTER TABLE event
--ADD COLUMN begin_date varchar(15),
--add column location varchar(25),
--add column time varchar(25),
--add column cost integer,
--add column grading_format varchar(25);

--ALTER TABLE individual
--ADD COLUMN fname varchar(15),
--add column lname varchar(25);

--insert into department(id, name, head)
insert into event(id, type, begin_date, location, time, cost, grading_format, description) values(default, 1, '2020-12-17', 'orem', '8am', 3500, 'A-F', 'a regular old computer science university course');
insert into reimbursement(id, status, event, employee, amount) values(default, 1, 1, 1, 3500);

insert into event(id, type, begin_date, location, time, cost, grading_format) values(default, 2, '2021-01-05');
insert into reimbursement(id, status, event, employee, amount) values(default, );

insert into event(id, type, begin_date, location, time, cost, grading_format) values(default, 3, '2021-02-12');
insert into reimbursement(id, status, event, employee, amount) values(default, );

insert into event(id, type, begin_date, location, time, cost, grading_format) values(default, 4, '2021-05-05');
insert into reimbursement(id, status, event, employee, amount) values(default, );

insert into event(id, type, begin_date, location, time, cost, grading_format) values(default, 5, '2021-11-05');
insert into reimbursement(id, status, event, employee, amount) values(default, );

--insert into individual(id, role, name, passwd) 
--values(default, 2, 'kyle', 'pass');
