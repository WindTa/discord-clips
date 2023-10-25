# discord-clips
- This is a frontend + backend application for Discord users to save YouTube clips and optionally playlists of YouTube clips.
- I will manage this project using Jira Scrum board to track tasks and story points
- Each Discord user can:
	- view/save/edit/delete YouTube clips
	- view/save/edit/delete playlists
	- view their Discord servers
	- for each server, view enabled YouTube clips (Discord bot purposes)
	- for each server, enable and disable YouTube clips (Discord bot purposes)
- Frameworks and Libraries: Discord.js, React, Node, Spring Boot, Maven, MySQL, Mockito
- Learning Goals: learn to use discord.js 14, embedded media players, and OAuth2. 
---
## Diagrams and Wireframes
- [Database Schema](./imgs/database-schema.png)
- [Layers](./imgs/layers.png)
- [UI Sketch and Transitions](./imgs/ui-sketch.png)

---
## User Stories
### As a user I want to ...
- sign in to my Discord account
- sign out of my Discord account
- toggle the menu sidebar
- view a tutorial on how to use the application (HOME)
- enter YouTube url to view at SAVE_CLIP page
	- to save start time, end time, volume control, and playback speed
	- create new playlist from select playlist view
	- view a list of playlists to save to
- view all of my clips, playlists, and servers in carousel view at LIBRARY page
- view all of and minimize my playlists in the menu sidebar
- view all of and minimize my servers in the menu sidebar
- view all clips in a playlist at PLAYLIST page
	- delete playlist
	- view clip info
	- play clip through a mini player as a preview
	- click options for edit/delete/save to playlist
- view all playlists at SERVER page
	- view all enabled clips
	- view all clips per playlist
	- view unassigned clips (not in a playlist)
	- enable/disable clip for server
---
## Install Guide
### Discord Application
- [Login to your Discord here](https://discord.com/developers/applications)
- Create a Discord Application
- Go to OAuth2
	- Save `Client ID`
	- Save `Client Secret`
	- Enter `Callback URL` into Redirects
### Supabase
- [Login to your Github here](https://supabase.com/)
- Create a Supabase Project
	- Save `Database Password` just in case you ever need it
- Go to Settings -> API
	- Save `URL`
	- Save `Project API Key anon public`
-  Go to Authentication -> URL Configuration
	- Enter a url you wish to redirect to after authenticating
- Go to Authentication -> Providers -> Discord
	- Enable
	- Enter `Client ID` from Discord Project
	- Enter `Client Secret` from Discord Project
	- Save `Callback URL`
### React App
- Create react app with `npx create-react-app react-app-name`
#### Libaries
- Supabase: `npm install @supabase/auth-ui-react @supabase/supabase-js`
- Routing: `npm install react-router-dom`
- Bootstrap: `npm install bootstrap react-bootstrap react-router-bootstrap`
---
## Tasks
### ~~Research  (Estimate: 4 hours | Actual: 6 hours)~~
- [x] YouTube Embed
- [x] Discord.js Audio Stream
- [x] Discord.js OAuth2
### ~~Diagrams  (Estimate: 4 hours | Actual: 4 hours)~~
- [x] Database schema
- [ ] Class
- [x] Layers
- [x] Flow
- [x] UI Sketch and Transitions
### ~~Create Task List  (Estimate: 2 hours | Actual: 2 hours)~~
### ~~Database  (Estimate: 2 hours | Actual:  2 hours)~~
- [x] Design schema
- [x] Create DML schema
- [x] Create test DML schema
- [x] Create known good state stored procedure for testing purposes
### ~~Implement DiscordUser  (Estimate: 2 hours | Actual:  3 hours)~~
- [x] Add `DiscordUserJdbcTemplateRepository` class
	- [x] `DiscordUser findById(int discordUserId)`
	- [x] `DiscordUser add(DiscordUser discordUser)`
	- [x] `boolean update(DiscordUser discordUser)`
	- [x] `boolean deleteById(int discordUserId)`
- [x] Add `DiscordUserRepositoryTest` class
- [x] Extract `DiscordUserRepository` interface
- [x] Add `DiscordUserService` class
	- [x] `Result<DiscordUser> add(DiscordUser discordUser)`
	- [x] `Result<DiscordUser> update(DiscordUser discordUser)`
	- [x] `boolean deleteById(int discordUserId)`
 - [x] Add `DiscordUserServiceTest` class
 - [x] Add `DiscordUserController` class
	 - [x] `@PostMapping ResponseEntity<Object> add(@RequestBody DiscordUser discordUser)`
	 - [x] `@PutMapping("/{discordUserId}")` `ResponseEntity<Object> update(@PathVariable int discordUserId, @RequestBody DiscordUser discordUser)`
	 - [x] `@DeleteMapping("/{discordUserId}")` `ResponseEntity<Void> deleteById(@PathVariable int discordUserId)`
### ~~Implement DiscordServer  (Estimate: 2 hours | Actual:  2 hours)~~
- [x] Add `DiscordServerJdbcTemplateRepository` class
	- [x] `DiscordServer findById(int discordServerId)`
		- [x] `addClips()`
	- [x] `DiscordServer add(DiscordServer discordServer)`
	- [x] `boolean update(DiscordServer discordServer)`
	- [x] `boolean deleteById(int discordServerId)`
- [x] Add `DiscordServerRepositoryTest` class
- [x] Extract `DiscordServerRepository` interface
- [x] Add `DiscordServerService` class
	- [x] `Result<DiscordServer> add(DiscordServer discordServer)`
	- [x] `Result<DiscordServer> update(DiscordServer discordServer)`
	- [x] `boolean deleteById(int discordServerId)`
 - [x] Add `DiscordServerServiceTest` class
 - [x] Add `DiscordServerController` class
	 - [x] `@PostMapping ResponseEntity<Object> add(@RequestBody DiscordServer discordServer)`
	 - [x] `@PutMapping("/{discordServerId}")` `ResponseEntity<Object> update(@PathVariable int discordServerId, @RequestBody DiscordServer discordServer)`
	 - [x] `@DeleteMapping("/{discordServerId}")` `ResponseEntity<Void> deleteById(@PathVariable int discordServerId)`
### ~~Implement YouTube Clip  (Estimate: 2 hours | Actual:  3 hours)~~
- [x] Add `ClipJdbcTemplateRepository` class
	- [x] `List<Clip> findByUser(int userId)`
	- [x] `List<Clip> findByServer(int serverId)`
	- [x] `List<Clip> findDefault(int userId)`
	- [x] `Clip findById(int clipId)`
	- [x] `Clip add(Clip Clip)`
	- [x] `boolean update(Clip Clip)`
	- [x] `boolean deleteById(int clipId)`
- [x] Add `ClipRepositoryTest` class
- [x] Extract `ClipRepository` interface
- [x] Add `ClipService` class
	- [x] `Result<Clip> add(Clip clip)`
	- [x] `Result<Clip> update(Clip clip)`
	- [x] `boolean deleteById(int clipId)`
 - [x] Add `ClipServiceTest` class
 - [x] Add `ClipController` class
	 - [x] `@PostMapping ResponseEntity<Object> add(@RequestBody Clip clip)`
	 - [x] `@PutMapping("/{clipId}")` `ResponseEntity<Object> update(@PathVariable int clipId, @RequestBody Clip clip)`
	 - [x] `@DeleteMapping("/{clipId}")` `ResponseEntity<Void> deleteById(@PathVariable int clipId)`
### Implement ServerClip  (Estimate: 2 hours | Actual:  1 hours)
- [x] Add `ServerClipJdbcTemplateRepository` class
	- [x] `ServerClip add(ServerClip serverClip)`
	- [x] `boolean update(ServerClip serverClip)`
	- [x] `boolean deleteByKey(int serverId, int clipId)`
- [x] Add `ServerClipRepositoryTest` class
- [x] Extract `ServerClipRepository` interface
- [x] Add `ServerClipService` class
	- [x] `Result<ServerClip> add(ServerClip serverClip)`
	- [x] `Result<ServerClip> update(ServerClip serverClip)`
	- [x] `boolean deleteByKey(int serverId, int clipId)`
 - [x] Add `ServerClipServiceTest` class
 - [x] Add `ServerClipController` class
	 - [x] `@PostMapping ResponseEntity<Object> add(@RequestBody ServerClip serverClip)`
	 - [x] `@PutMapping` `ResponseEntity<Object> update(@RequestBody ServerClip serverClip)`
	 - [x] `@DeleteMapping("/{serverId}/{clipId}")` `ResponseEntity<Void> deleteByKey(@PathVariable int serverId, @PathVariable int clipId)`
### ~~Implement Playlist  (Estimate: 2 hours | Actual:  3 hours)~~
- [x] Add `PlaylistJdbcTemplateRepository` class
	- [x] `List<Playlist> findByUserId(int userId)`
	- [x] `Playlist findById(int playlistId)`
	- [x] `Playlist add(Playlist playlist)`
	- [x] `boolean update(Playlist playlist)`
	- [x] `boolean deleteById(int playlistId)`
- [x] Add `PlaylistRepositoryTest` class
- [x] Extract `PlaylistRepository` interface
- [x] Add `PlaylistService` class
	- [x] `Result<Playlist> add(Playlist playlist)`
	- [x] `Result<Playlist> update(Playlist playlist)`
	- [x] `boolean deleteById(int playlistId)`
 - [x] Add `PlaylistServiceTest` class
 - [x] Add `PlaylistController` class
	 - [x] `@PostMapping ResponseEntity<Object> add(@RequestBody Playlist playlist)`
	 - [x] `@PutMapping("/{playlistId}")` `ResponseEntity<Object> update(@PathVariable int playlistId, @RequestBody Playlist playlist)`
	 - [x] `@DeleteMapping("/{playlistId}")` `ResponseEntity<Void> deleteById(@PathVariable int playlistId)`
### ~~Implement PlaylistClip  (Estimate: 2 hours | Actual:  1 hours)~~
- [x] Add `PlaylistClipJdbcTemplateRepository` class
	- [x] `PlaylistClip add(PlaylistClip playlistClip)`
	- [x] `boolean update(PlaylistClip playlistClip)`
	- [x] `boolean deleteByKey(int playlistId, int clipId)`
- [x] Add `PlaylistClipRepositoryTest` class
- [x] Extract `PlaylistClipRepository` interface
- [x] Add `PlaylistClipService` class
	- [x] `Result<PlaylistClip> add(PlaylistClip playlistClip)`
	- [x] `Result<PlaylistClip> update(PlaylistClip playlistClip)`
	- [x] `boolean deleteByKey(int playlistId, int clipId)`
 - [x] Add `PlaylistClipServiceTest` class
 - [x] Add `PlaylistClipController` class
	 - [x] `@PostMapping ResponseEntity<Object> add(@RequestBody PlaylistClip playlistClip)`
	 - [x] `@PutMapping` `ResponseEntity<Object> update(@RequestBody PlaylistClip playlistClip)`
	 - [x] `@DeleteMapping("/{playlistId}/{clipId}")` `ResponseEntity<Void> deleteByKey(@PathVariable int playlistId, @PathVariable int clipId)`
### Discord Authentication  (Estimate: 4 hours | Actual:  8+ hours)
- [ ] Create Spring Boot application
	- [ ] Configure to support Discord as a OAuth2 provider
	- [ ] Add `SecurityConfig` class
		- [ ] Update with new permissions after each feature implementation
	- [ ] Add `AuthController` class
		- [ ] Block a request if requests have an invalid access token
- [ ] Create Discord application
	- [ ] Generate OAuth2 Link with permissions and redirect
	- [ ] Create Route to OAuth2 Link
	- [ ] Any requests made will need the access token
### UI Services  (Estimate: 4 hours | Actual:  hours)
- [ ] Add `discordUser.js`
- [ ] Add `discordServer.js`
- [ ] Add `clip.js`
- [ ] Add `userServerClip.js`
- [ ] Add `userDefaultClip.js`
- [ ] Add `playlist.js`
- [ ] Add `playlistClip.js`
### ~~NavBar component  (Estimate: 1 hours | Actual:  hours)~~
- [x] Menu toggle button
- [x] Home Link
- [x] Search component + search button
- [x] Sign In / Sign Out
### ~~Menu component  (Estimate: 1 hours | Actual:  hours)~~
- [x] Home Link
- [x] Library Link
- [x] Playlist Option
- [x] Server Option
- [x] Logged In/Logged Out View
- [x] Hidden (partial/full)
### ClipInfo component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] Video Title
- [ ] Video | Release Date
- [ ] Channel Picture | Channel Name
- [ ] Description
### ClipOptions component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] Default ..., togglable to 
- [ ] Edit/Delete/Save to Playlist
### Clip component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] Thumbnail
- [ ] ClipInfo component
- [ ] Mini Player
- [ ] ClipOptions component
- [ ] = Swappable button
### ClipList component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] Map Clips to Clip components
### Home/Guide page  (Estimate: 2 hours | Actual:  hours)
- [ ] What does it do?
- [ ] How to get started?
- [ ] Add to your server!
- [ ] Save a clip!
### ~~Library page  (Estimate: 2 hours | Actual:  hours)~~
- [x] Clips carousel
- [x] Playlist carousel
- [x] Server carousel
### PlaylistHeader component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] Playlist Name
- [ ] Playlist Name Editor
- [ ] Playlist Search component
- [ ] Delete Playlist button
### Playlist page  (Estimate: 1 hours | Actual:  hours)
- [ ] PlaylistHeader component
- [ ] Add to Playlist button
- [ ] ClipList component
### SaveClip page  (Estimate: 2 hours | Actual:  hours)
- [ ] YouTube embed
- [ ] Media control
- [ ] Clip Info component
- [ ] Save button
### ServerHeader component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] ServerInfo component
- [ ] Server Clips Search component
- [ ] Edit Clips
### Server page  (Estimate: 1 hours | Actual:  hours)
- [ ] ServerHeader component
- [ ] ClipList component
### ServerPlaylist component  (Estimate: 1 hours | Actual:  hours)
- [ ] PlaylistInfo component
- [ ] Playlist Enable button
### ServerPlaylistEdit component  (Estimate: 1 hours | Actual:  hours)
- [ ] Clip component
---
