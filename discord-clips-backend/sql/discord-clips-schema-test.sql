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

create table playlist (
	playlist_id int primary key auto_increment,
    playlist_name varchar(50) not null,
	discord_user_id bigint not null,
    constraint fk_playlist_discord_user_id
		foreign key (discord_user_id)
        references discord_user(discord_user_id)
);

create table clip (
	clip_id int primary key auto_increment,
    clip_name varchar(50) not null,
    youtube_id varchar(11) not null,
    start_time decimal not null,
    end_time decimal not null,
    volume decimal not null,
    playback_speed decimal not null,
    discord_user_id bigint not null,
    constraint fk_clip_discord_user_id
		foreign key (discord_user_id)
        references discord_user(discord_user_id)
);

create table playlist_clip (
	playlist_id int not null,
    clip_id int not null,
	display_order int not null,
    constraint pk_playlist_clip
		primary key (playlist_id, clip_id),
	constraint fk_playlist_clip_playlist_id
		foreign key (playlist_id)
        references playlist(playlist_id),
	constraint fk_playlist_clip_clip_id
		foreign key (clip_id)
        references clip(clip_id)
);

create table discord_server_clip (
	discord_server_id bigint not null,
    clip_id int not null,
    constraint pk_discord_server_clip
		primary key (discord_server_id, clip_id),
	constraint fk_discord_server_clip_discord_server_id
		foreign key (discord_server_id)
        references discord_server(discord_server_id),
	constraint fk_discord_server_clip_clip_id
		foreign key (clip_id)
        references clip(clip_id)
);

-- insert default values
insert into discord_user (discord_user_id, username) values
	(221863292681977857, 'WindTa'),
    (400148172724502538, 'Xong Xina');

insert into discord_server (discord_server_id, servername) values
	(0, "Default"),
    (1161381438839607358, "WindTa's server");
    
insert into playlist (playlist_name, discord_user_id) values
	('My Playlist', 221863292681977857);
    
insert into clip (clip_name, youtube_id, start_time, end_time, volume, playback_speed, discord_user_id) values
	('My Clip', 'fSKQRDq3RkM', 5.0, 10.0, 1.0, 1.0, 221863292681977857),
    ('My Clip', 'dQw4w9WgXcQ', 5.0, 10.0, 1.0, 1.0, 400148172724502538);
    
    
insert into playlist_clip (playlist_id, clip_id, display_order) values
	(1, 1, 1);
    
insert into discord_server_clip (discord_server_id, clip_id) values
	(1161381438839607358, 1),
    (0, 2);
    
-- set known good state
delimiter //
create procedure set_known_good_state()
begin

	delete from discord_server_clip;
    delete from playlist_clip;
    
    delete from clip;
    alter table clip auto_increment = 1;
    delete from playlist;
    alter table playlist auto_increment = 1;
    
    delete from discord_server;
    delete from discord_user;

    insert into discord_user (discord_user_id, username) values
	(221863292681977857, 'WindTa'),
    (400148172724502538, 'Xong Xina');

	insert into discord_server (discord_server_id, servername) values
		(0, "Default"),
		(1161381438839607358, "WindTa's server");
		
	insert into playlist (playlist_name, discord_user_id) values
		('My Playlist', 221863292681977857);
		
	insert into clip (clip_name, youtube_id, start_time, end_time, volume, playback_speed, discord_user_id) values
		('My Clip', 'fSKQRDq3RkM', 5.0, 10.0, 1.0, 1.0, 221863292681977857),
		('My Clip', 'dQw4w9WgXcQ', 5.0, 10.0, 1.0, 1.0, 400148172724502538);
		
		
	insert into playlist_clip (playlist_id, clip_id, display_order) values
		(1, 1, 1);
		
	insert into discord_server_clip (discord_server_id, clip_id) values
		(1161381438839607358, 1),
		(0, 2);
    
end //
delimiter ;
