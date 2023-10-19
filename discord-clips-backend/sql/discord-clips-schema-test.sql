drop database if exists discord_clips_test;
create database discord_clips_test;
use discord_clips_test;

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
    
-- set known good state
delimiter //
create procedure set_known_good_state()
begin

	delete from discord_user;
    delete from discord_server;

    insert into discord_user (discord_user_id, username) values
        (221863292681977857, 'windta');

    insert into discord_server (discord_server_id, servername) values
        (1161381438839607358, "WindTa's server");
    
end //
delimiter ;
