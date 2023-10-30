# Tasks
## ~~Research  (Estimate: 4 hours | Actual: 6 hours)~~
- [x] YouTube Embed
- [x] Discord.js Audio Stream
- [x] Discord.js OAuth2
## ~~Diagrams  (Estimate: 4 hours | Actual: 4 hours)~~
- [x] Database schema
- [ ] Class
- [x] Layers
- [x] Flow
- [x] UI Sketch and Transitions
## ~~Create Task List  (Estimate: 2 hours | Actual: 2 hours)~~
## ~~Database  (Estimate: 2 hours | Actual:  2 hours)~~
- [x] Design schema
- [x] Create DML schema
- [x] Create test DML schema
- [x] Create known good state stored procedure for testing purposes
## ~~Implement DiscordUser  (Estimate: 2 hours | Actual:  3 hours)~~
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
## ~~Implement DiscordServer  (Estimate: 2 hours | Actual:  2 hours)~~
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
## ~~Implement YouTube Clip  (Estimate: 2 hours | Actual:  3 hours)~~
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
## Implement ServerClip  (Estimate: 2 hours | Actual:  1 hours)
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
## ~~Implement Playlist  (Estimate: 2 hours | Actual:  3 hours)~~
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
## ~~Implement PlaylistClip  (Estimate: 2 hours | Actual:  1 hours)~~
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
## Discord Authentication  (Estimate: 4 hours | Actual:  8+ hours)
- [ ] Create Spring Boot application
	- [ ] Configure to support Discord as a OAuth2 provider
	- [ ] Add `SecurityConfig` class
		- [ ] Update with new permissions after each feature implementation
	- [ ] Add `AuthController` class
		- [ ] Block a request if requests have an invalid access token
- [x] Create Discord application
	- [x] Generate OAuth2 Link with permissions and redirect
	- [x] Create Route to OAuth2 Link
	- [x] Any requests made will need the access token
## ~~UI Services  (Estimate: 4 hours | Actual:  4 hours)~~
- [x] Add `discordUser.js`
- [x] Add `discordServer.js`
- [x] Add `clip.js`
- [x] Add `userServerClip.js`
- [x] Add `userDefaultClip.js`
- [x] Add `playlist.js`
- [x] Add `playlistClip.js`
## ~~NavBar component  (Estimate: 1 hours | Actual:  2 hours)~~
- [x] Menu toggle button
- [x] Home Link
- [x] Search component + search button
- [x] Sign In / Sign Out
## ~~Menu component  (Estimate: 1 hours | Actual: 4 hours)~~
- [x] Home Link
- [x] Library Link
- [x] Playlist Option
- [x] Server Option
- [x] Logged In/Logged Out View
- [x] Hidden (partial/full)
## ~~ClipInfo component  (Estimate: 0.5 hours | Actual:  1 hours)~~
- [x] Video Title
- [x] Video | Release Date
- [x] Channel Picture | Channel Name
- [x] Description
## ClipOptions component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] Default ..., togglable to 
- [ ] Edit/Delete/Save to Playlist
## ~~Clip component  (Estimate: 0.5 hours | Actual:  1 hours)~~
- [x] Thumbnail
- [ ] ClipInfo component
- [ ] Mini Player
- [ ] ClipOptions component
- [ ] = Swappable button
## ~~ClipList component  (Estimate: 0.5 hours | Actual:  2 hours)~~
- [x] Map Clips to Clip components
## Home/Guide page  (Estimate: 2 hours | Actual:  hours)
- [ ] What does it do?
- [ ] How to get started?
- [ ] Add to your server!
- [ ] Save a clip!
## ~~Library page  (Estimate: 2 hours | Actual:  4 hours)~~
- [x] Clips carousel
- [x] Playlist carousel
- [x] Server carousel
## PlaylistHeader component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] Playlist Name
- [ ] Playlist Name Editor
- [ ] Playlist Search component
- [ ] Delete Playlist button
## Playlist page  ~~(Estimate: 1 hours | Actual:  1 hours)~~
- [x] PlaylistHeader component
- [ ] Add to Playlist button
- [x] ClipList component
## ~~SaveClip page  (Estimate: 2 hours | Actual:  8 hours)~~
- [x] YouTube embed
- [x] Media control
- [x] Clip Info component
- [x] Save button
## ServerHeader component  (Estimate: 0.5 hours | Actual:  hours)
- [ ] ServerInfo component
- [ ] Server Clips Search component
- [ ] Edit Clips
## ~~Server page  (Estimate: 1 hours | Actual:  1 hours)~~
- [ ] ServerHeader component
- [ ] ClipList component
## ServerPlaylist component  (Estimate: 1 hours | Actual:  hours)
- [ ] PlaylistInfo component
- [ ] Playlist Enable button
## ServerPlaylistEdit component  (Estimate: 1 hours | Actual:  hours)
- [ ] Clip component
---
