drop database if exists discord_clips;
create database discord_clips;
use discord_clips;

-- create tables and relationships
create table discord_user (
	discord_user_id bigint primary key,
    username varchar(32) not null
);

create table discord_server (
    discord_server_id bigint primary key,
    servername varchar(32) not null
);

-- insert default values
insert into discord_user (discord_user_id, username) values
	(221863292681977857, 'windta');

insert into discord_server (discord_server_id, servername) values
    (1161381438839607358, "WindTa's server");