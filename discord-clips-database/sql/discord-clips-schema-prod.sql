drop database if exists discord_clips;
create database discord_clips;
use discord_clips;

-- create tables and relationships
create table discord_user (
	discord_user_id int primary key auto_increment,
    username varchar(32) not null
);