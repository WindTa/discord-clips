drop database if exists discord_clips_test;
create database discord_clips_test;
use discord_clips_test;

-- create tables and relationships
create table discord_user (
	discord_user_id bigint primary key,
    username varchar(32) not null
);

-- insert default values
insert into discord_user (discord_user_id, username) values
	(221863292681977857, 'windta');