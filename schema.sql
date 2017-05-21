drop table if exists user_info.user_info;

create table user_info.user_info 
(
	name 		varchar(255) 	not null,
	NIF 		integer 	not null unique,
	address 	varchar(1023) 	not null,
	phone_number 	varchar(20) 	not null,
	primary key(NIF)
);
