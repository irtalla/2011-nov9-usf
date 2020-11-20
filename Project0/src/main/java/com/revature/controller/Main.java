package com.revature.controller;


import com.revature.controller.Application;

public class Main {
	
	public static void main(String... args) {
		
		Application app = Application.getApplication(); 
		app.init();

		
//		while ( app.isRunning() ) { 
//			app.getUserResponse(); 
//		}
		
	}

}

/*

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

create table model (
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
	model integer references model
);

create table product_feature (
	id serial primary key,
	name varchar(40) unique not null
);


*/
