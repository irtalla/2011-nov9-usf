--Clearing the database
drop table if exists roles cascade;
drop table if exists persons cascade;
drop table if exists genres cascade;
drop table if exists story_types cascade;
drop table if exists status cascade;
drop table if exists priorities cascade;
drop table if exists story_pitches cascade;
drop table if exists genre_specializations cascade;
drop table if exists change_requests cascade;
drop table if exists info_responses cascade;
drop table if exists info_requests cascade;
drop table if exists approvals cascade;
drop table if exists rejections cascade;

--DDL
create table roles(
role_id integer primary key,
role_name varchar(30) not null
);

create table persons(
person_id serial primary key,
name varchar(50) not null,
password varchar(50),
points numeric default 100,
role_id integer references roles,
username varchar(50) unique
);

create table genres(
genre_id serial primary key,
genre_name varchar(30)
);

create table story_types(
type_id serial primary key,
type_name varchar(30)
);

create table status(
status_id int primary key,
status_name varchar(100)
);

create table priorities(
priority_id serial primary key,
priority_name varchar(30)
);

create table story_pitches(
story_id serial primary key,
title varchar(50),
type_id int references story_types,
submission_date date not null default current_date,
completion_date date not null,
tag_line varchar(1000),
detailed_description varchar(10000),
person_id int references persons,
genre_id int references genres,
status_id int references status default 1,
priority_id int references priorities default 1,
changes_requested int default 0,
draft text
);

create table approvals(
approval_id serial primary key,
approval_date date default current_date,
status_approved int references status,
approver_id int references persons,
approved_id int references persons,
story_approved_id int references story_pitches
);

create table rejections(
rejection_id serial primary key,
rejection_date date default current_date,
reason varchar(1000),
rejector_id int references persons,
rejected_id int references persons,
story_rejected int references story_pitches
);

create table change_requests(
request_id serial primary key,
request_date date default current_date,
message varchar(10000),
story_id int references story_pitches,
person_requesting int references persons,
person_requested int references persons
);

create table info_requests(
request_id serial primary key,
request_date date default current_date,
message varchar(10000),
story_id int references story_pitches,
person_requesting int references persons,
person_requested int references persons
);

create table info_responses(
response_id serial primary key,
response_date date default current_date,
request_id int references info_requests,
message varchar(10000),
story_id int references story_pitches,
person_responding int references persons,
person_responded int references persons
);

create table genre_specializations(
genre_specializations_id serial primary key,
person_id int references persons,
genre_id int references genres
);

-- Populating the database
insert into roles values
	(1, 'Senior Editor'),
	(2, 'Assosciate Editor'),
	(3, 'Assistant Editor'),
	(4, 'Writer');

insert into story_types values
	(1, 'Novel'),
	(2, 'Novella'),
	(3, 'Short Story'),
	(4, 'Article');

insert into status values
	(0, 'On Hold'),
	(1, 'Pitch Under Review - Assistant Genre Editor Approval Needed'),
	(2, 'Pitch Under Review - General Editor Approval Needed'),
	(3, 'Pitch Under Review - Senior Genre Editor Approval Needed'),
	(4, 'Pitch Modified - Author Approval Needed'),
	(5, 'Pitch Approved - Awaiting Draft Submission'),
	(6, 'Draft Under Review - Comittee Approval Needed'),
	(7, 'Draft Accepted'),
	(8, 'Rejected'),
	(9, 'Withdrawn');

insert into priorities values
	(0, 'Nonexistent'),
	(1, 'Low'),
	(2, 'High');

insert into genres values
	(default, 'History'),							-- 1
	(default, 'Biography'),							-- 2
	(default, 'Science and Technology'),			-- 3
	(default, 'Poetry'),							-- 4
	(default, 'Non-Fiction'),						-- 5
	(default, 'Contemporary Fiction'),				-- 6
	(default, 'Historical Fiction'),				-- 7
	(default, 'Juvenile Fiction'),					-- 8 
	(default, 'Juvenile Non-Fiction'),				-- 9
	(default, 'Science Fiction/Fantasy'),			-- 10
	(default, 'Mystery'),							-- 11
	(default, 'Thriller'),							-- 12
	(default, 'Horror');							-- 13

insert into persons values
	(default, 'Harriet McDougal', 'asdf', default, 1, 'user1'),		-- 1
	(default, 'J. R. R. Tolkien', 'asdf', default, 1, 'user2'),		-- 2
	(default, 'Edgar Allan Poe', 'asdf', default, 1, 'user3'),		-- 3
	(default, 'Isaac Asimov', 'asdf', default, 1, 'user4'),			-- 4
	(default, 'Isaac Newton', 'asdf', default, 2, 'user5'),			-- 5
	(default, 'Jane Austen', 'asdf', default, 1, 'user6'),			-- 6
	(default, 'Arthur Conan Doyle', 'asdf', default, 1, 'user7'),	-- 7
	(default, 'Plutarch', 'asdf', default, 1, 'user8'),				-- 8
	(default, 'Robert Louis Stevenson', 'asdf', default, 1, 'user9'),-- 9
	(default, 'Jan Berenstain', 'asdf', default, 1, 'user10'),		-- 10
	(default, 'Stan Berenstain', 'asdf', default, 2, 'user11'),		-- 11
	(default, 'Herman Melville', 'asdf', default, 2, 'user12'),		-- 12
	(default, 'Emily Dickinson', 'asdf', default, 2, 'user13'),		-- 13
	(default, 'Robert Frost', 'asdf', default, 2,' user14'),		-- 14
	(default, 'Charlotte Bronte', 'asdf', default, 2, 'user15'),	-- 15
	(default, 'Charles Dickens', 'asdf', default, 2, 'user16'),		-- 16
	(default, 'Ellis Peters', 'asdf', default, 2, 'user17'),		-- 17
	(default, 'Graham Greene', 'asdf', default, 2, 'user18'),		-- 18
	(default, 'Frank Herbert', 'asdf', default, 2, 'user19'),		-- 19
	(default, 'Jack Vance', 'asdf', default, 2, 'user20'),			-- 20
	(default, 'H. P. Lovecraft', 'asdf', default, 2, 'user21'),		-- 21
	(default, 'Livy', 'asdf', default, 2, 'user22'),					-- 22
	(default, 'Seutonius', 'asdf', default, 2, 'user23'),				-- 23
	(default, 'Ted Yeatman', 'asdf', default, 2, 'user24'),			-- 24
	(default, 'Andrew Wiles', 'asdf', default, 2, 'user25'),			-- 25
	(default, 'John Brown', 'asdf', default, 3, 'user26'),			-- 26
	(default, 'Jane Doe', 'asdf', default, 3, 'user27'),				-- 27
	(default, 'Brandon Sanderson', 'asdf', default, 4, 'user28'), 
	(default, 'Ray Bradbury', 'asdf', default, 4, 'user29'),
	(default, 'Simon Kuper', 'asdf', default, 4, 'user30'),
	(default, 'David McCullough', 'asdf', default, 4, 'user31'),
	(default, 'Daniel Abraham', 'asdf', default, 4, 'user32'),		-- 32
	(default, 'G. J. Meyer', 'asdf', default, 4, 'user33'),
	(default, 'Stephen King', 'asdf', default, 4, 'user34');

insert into genre_specializations values
	(default, 1, 10),
	(default, 1, 5),
	(default, 1, 6),
	(default, 2, 10),
	(default, 3, 4),
	(default, 3, 13),
	(default, 4, 10),
	(default, 5, 3),
	(default, 6, 6),
	(default, 6, 7),
	(default, 7, 7),
	(default, 7, 11),
	(default, 8, 1),
	(default, 8, 2),
	(default, 9, 12),
	(default, 9, 13),
	(default, 10, 8),
	(default, 10, 9),
	(default, 11, 8),
	(default, 11, 9),
	(default, 12, 7),
	(default, 13, 4),
	(default, 14, 4),
	(default, 15, 7),
	(default, 16, 7),
	(default, 17, 7),
	(default, 17, 11),
	(default, 18, 12),
	(default, 18, 6),
	(default, 19, 10),
	(default, 20, 10),
	(default, 21, 12),
	(default, 21, 13),
	(default, 22, 1),
	(default, 22, 2),
	(default, 23, 1),
	(default, 23, 2),
	(default, 24, 1),
	(default, 24, 2),
	(default, 25, 3),
	(default, 26, 1),
	(default, 26, 2),
	(default, 26, 3),
	(default, 26, 4),
	(default, 26, 5),
	(default, 26, 6),
	(default, 26, 7),
	(default, 26, 8),
	(default, 26, 9),
	(default, 26, 10),
	(default, 26, 11),
	(default, 26, 12),
	(default, 27, 1),
	(default, 27, 2),
	(default, 27, 3),
	(default, 27, 4),
	(default, 27, 5),
	(default, 27, 6),
	(default, 27, 7),
	(default, 27, 8),
	(default, 27, 9),
	(default, 27, 10),
	(default, 27, 11),
	(default, 27, 12);



insert into story_pitches values
	(default, 'Knight of Wisdom', 1, default, '2022-05-05',
	'The epic conclusion of the award winning Stormlight Archive series.', 
	'The end of the first, five-book cycle in the Stormlight Archive will see the fate of Rosharan system decided, with the the Voidspren-enhanced Parshendi Army 
	met the alliace of Alethkar, Jah Kahved and the Knights Radiant led by the returned Heralds. 
	Odium will be banished, but Honor will be unable to defeat him utterly, laying in place the setup for the second quintet of books in the Stormlight Archive.',
	28, 10, 1, 1, 0, 
	Null),
	(default, 'Leviathan Roars', 3, default, '2021-06-01',
	'Can humanity accept a fate in which they are but a part of a higher consciousness? Can they even survive?', 
	'A sequel to Leviathan Wakes, humanity in this story must find a way to communmicate one level up - or risk being cut off like a diseased limb.',
	32, 10, 3, 1, 0, 
	Null),
	(default, 'Mistborn Era 2 Book 4', 1, default, '2021-9-01',
	'The final installment of the Wax and Wayne Tetrology', 
	'TBD',
	28, 10, 0, 1, 0, 
	Null),
	(default, 'Stormlight 4.5', 2, default, '2022-3-15',
	'A novella centered on the character Rock. Chronologically it will take place before book 1, but will be released between books 4 and 5', 
	'TBD',
	28, 10, 0, 1, 0, 
	Null),
	(default, 'Bleak Expectations', 1, default, '1860-3-15',
	'Really bleak expectations', 
	'TBD',
	16, 7, 1, 1, 0, 
	null);

update persons set points = 50 where person_id = 28;
update persons set points = 80 where person_id = 32;

insert into approvals values(default, default, 2, 26, 28, 1);
update story_pitches
set status_id = 2
where story_id = 1;

insert into approvals values(default, default, 3, 18, 28, 1);
update story_pitches
set status_id = 3
where story_id = 1;

insert into approvals values(default, default, 5, 1, 28, 1);
update story_pitches
set status_id = 4
where story_id = 1;

insert into rejections values(default, default, 'You are dead and not allowed to publish anymore', 6, 16, 5);
update story_pitches
set status_id = 8, priority_id =0
where story_id = 5;

insert into change_requests values 
	(default, default, 'Come up with a better tagline', 2, 26, 32),
	(default, default, 'Is there any way you could move the publication date forward to the first quarter?', 1, 1, 28);

insert into info_requests values 
	(default, default, 'Were you planning on publishing this as a standalone, or as part of a magazine or anthology?', 2, 26, 32),
	(default, default, 'In what range do you expect the final word count to fall?', 1, 1, 28);

insert into info_responses values 
	(default, default, 1, 'I was thinking of publishing it as a standalone e-book, but would incorporate it into an anthology later.', 2, 33, 26),
	(default, default, 2, 'The final word count will probably be in the 450,000-500,000 range. I will try to keep it to 450,000, but I cannot guarantee it.', 1, 28, 1);

select * from change_requests;
select * from info_requests;
select * from info_responses;
select * from story_pitches;
select * from persons;
select * from rejections;
select * from approvals;

select p.person_id, p.name, r.role_name, g.genre_name, g.genre_id 
from genre_specializations gs
join persons p on p.person_id = gs.person_id 
join roles r on r.role_id = p.role_id 
join genres g on g.genre_id = gs.genre_id;

select g.genre_name, count(*) as Number_of_Editors
from persons p
join genre_specializations gs on p.person_id = gs.person_id
join genres g on g.genre_id = gs.genre_id 
group by g.genre_name, gs.genre_id 
order by g.genre_name;

select * from story_pitches sp join genres g on sp.genre_id = g.genre_id ;

select * from story_pitches sp where genre_id = 10 and status_id = 2;

select* from genre_specializations gs ;
