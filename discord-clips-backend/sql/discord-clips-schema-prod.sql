drop database if exists discord_clips;
create database discord_clips;
use discord_clips;

-- create tables and relationships
create table discord_user (
	discord_user_id bigint primary key,
    username varchar(32) not null
);