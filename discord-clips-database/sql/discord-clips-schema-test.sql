drop database if exists discord_clips_test;
create database discord_clips_test;
use discord_clips_test;

-- create tables and relationships
create table discord_user (
	discord_user_id bigint primary key,
    username varchar(32) not null
);

create table discord_role (
	discord_role_id int primary key auto_increment,
    rolename varchar(50) not null
);

create table discord_server (
	discord_server_id bigint primary key,
    servername varchar(32) not null
);

create table discord_user_role (
	discord_user_id bigint not null,
    discord_role_id int not null,
    discord_server_id bigint not null,
    constraint pk_discord_user_role
		primary key (discord_user_id, discord_role_id, discord_server_id),
	constraint fk_discord_user_role_user_id
		foreign key (discord_user_id)
        references discord_user(discord_user_id),
    constraint fk_discord_user_role_role_id
		foreign key (discord_role_id)
        references discord_role(discord_role_id),
    constraint fk_discord_user_role_server_id
		foreign key (discord_server_id)
        references discord_server(discord_server_id)
);

-- insert default values
insert into discord_user (discord_user_id, username) values
	(221863292681977857, 'windta');
    
insert into discord_role (rolename) values
	('SERVER_MEMBER'),
    ('SERVER_ADMIN'),
    ('APP_ADMIN');
    
insert into discord_server (discord_server_id, servername) values
	(0, 'DEFAULT');

insert into discord_user_role (discord_user_id, discord_role_id, discord_server_id) values
	(221863292681977857, 3, 0);