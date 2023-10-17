drop database if exists discord_clips_test;
create database discord_clips_test;
use discord_clips_test;

-- create tables and relationships
create table discord_user (
	discord_user_id int primary key auto_increment,
    username varchar(32) not null
);