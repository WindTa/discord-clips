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
## Tasks
### Research  (Estimate: 4 hours | Actual: 6 hours)
- [x] YouTube Embed
- [x] Discord.js Audio Stream
- [x] Discord.js OAuth2
### Diagrams  (Estimate: 4 hours | Actual: 4 hours)
- [x] Database schema
- [ ] Class
- [x] Layers
- [x] Flow
- [x] UI Sketch and Transitions
### Create Task List  (Estimate: 2 hours | Actual: 2 hours)
### Database  (Estimate: 2 hours | Actual:  hours)
- [ ] Design schema
- [ ] Create DML schema
- [ ] Create test DML schema
- [ ] Create known good state stored procedure for testing purposes
### Discord Authentication  (Estimate: 4 hours | Actual:  hours)
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
### Implement DiscordUser  (Estimate: 2 hours | Actual:  hours)
- [ ] Add `DiscordUserJdbcTemplateRepository` class
	- [ ] `DiscordUser findById(int discordUserId)`
	- [ ] `DiscordUser add(DiscordUser discordUser)`
	- [ ] `boolean update(DiscordUser discordUser)`
	- [ ] `boolean deleteById(int discordUserId)`
- [ ] Add `DiscordUserRepositoryTest` class
- [ ] Extract `DiscordUserRepository` interface
- [ ] Add `DiscordUserService` class
	- [ ] `Result<DiscordUser> add(DiscordUser discordUser)`
	- [ ] `Result<DiscordUser> update(DiscordUser discordUser)`
	- [ ] `boolean deleteById(int discordUserId)`
 - [ ] Add `DiscordUserServiceTest` class
 - [ ] Add `DiscordUserController` class
	 - [ ] `@PostMapping ResponseEntity<Object> add(@RequestBody DiscordUser discordUser)`
	 - [ ] `@PutMapping("/{discordUserId}")` `ResponseEntity<Object> update(@PathVariable int discordUserId, @RequestBody DiscordUser discordUser)`
	 - [ ] `@DeleteMapping("/{discordUserId}")` `ResponseEntity<Void> deleteById(@PathVariable int discordUserId)`
### Implement DiscordServer  (Estimate: 2 hours | Actual:  hours)
- [ ] Add `DiscordServerJdbcTemplateRepository` class
	- [ ] `DiscordServer findById(int discordServerId)`
		- [ ] `addClips()`
	- [ ] `DiscordServer add(DiscordServer discordServer)`
	- [ ] `boolean update(DiscordServer discordServer)`
	- [ ] `boolean deleteById(int discordServerId)`
- [ ] Add `DiscordServerRepositoryTest` class
- [ ] Extract `DiscordServerRepository` interface
- [ ] Add `DiscordServerService` class
	- [ ] `Result<DiscordServer> add(DiscordServer discordServer)`
	- [ ] `Result<DiscordServer> update(DiscordServer discordServer)`
	- [ ] `boolean deleteById(int discordServerId)`
 - [ ] Add `DiscordServerServiceTest` class
 - [ ] Add `DiscordServerController` class
	 - [ ] `@PostMapping ResponseEntity<Object> add(@RequestBody DiscordServer discordServer)`
	 - [ ] `@PutMapping("/{discordServerId}")` `ResponseEntity<Object> update(@PathVariable int discordServerId, @RequestBody DiscordServer discordServer)`
	 - [ ] `@DeleteMapping("/{discordServerId}")` `ResponseEntity<Void> deleteById(@PathVariable int discordServerId)`
### Implement YouTube Clip  (Estimate: 2 hours | Actual:  hours)
- [ ] Add `ClipJdbcTemplateRepository` class
	- [ ] `List<Clip> findByUser(int userId)`
	- [ ] `List<Clip> findByServer(int serverId)`
	- [ ] `List<Clip> findDefault(int userId)`
	- [ ] `Clip findById(int clipId)`
	- [ ] `Clip add(Clip Clip)`
	- [ ] `boolean update(Clip Clip)`
	- [ ] `boolean deleteById(int clipId)`
- [ ] Add `ClipRepositoryTest` class
- [ ] Extract `ClipRepository` interface
- [ ] Add `ClipService` class
	- [ ] `Result<Clip> add(Clip clip)`
	- [ ] `Result<Clip> update(Clip clip)`
	- [ ] `boolean deleteById(int clipId)`
 - [ ] Add `ClipServiceTest` class
 - [ ] Add `ClipController` class
	 - [ ] `@PostMapping ResponseEntity<Object> add(@RequestBody Clip clip)`
	 - [ ] `@PutMapping("/{clipId}")` `ResponseEntity<Object> update(@PathVariable int clipId, @RequestBody Clip clip)`
	 - [ ] `@DeleteMapping("/{clipId}")` `ResponseEntity<Void> deleteById(@PathVariable int clipId)`
### Implement UserServerClip  (Estimate: 2 hours | Actual:  hours)
- [ ] Add `UserServerClipJdbcTemplateRepository` class
	- [ ] `UserServerClip add(UserServerClip userServerClip)`
	- [ ] `boolean update(UserServerClip userServerClip)`
	- [ ] `boolean deleteByKey(int serverId, int clipId)`
- [ ] Add `UserServerClipRepositoryTest` class
- [ ] Extract `UserServerClipRepository` interface
- [ ] Add `UserServerClipService` class
	- [ ] `Result<UserServerClip> add(UserServerClip userServerClip)`
	- [ ] `Result<UserServerClip> update(UserServerClip userServerClip)`
	- [ ] `boolean deleteByKey(int serverId, int clipId)`
 - [ ] Add `UserServerClipServiceTest` class
 - [ ] Add `UserServerClipController` class
	 - [ ] `@PostMapping ResponseEntity<Object> add(@RequestBody UserServerClip userServerClip)`
	 - [ ] `@PutMapping` `ResponseEntity<Object> update(@RequestBody UserServerClip userServerClip)`
	 - [ ] `@DeleteMapping("/{serverId}/{clipId}")` `ResponseEntity<Void> deleteByKey(@PathVariable int serverId, @PathVariable int clipId)`
### Implement UserDefaultClip  (Estimate: 2 hours | Actual:  hours)
- [ ] Add `UserDefaultClipJdbcTemplateRepository` class
	- [ ] `UserDefaultClip add(UserDefaultClip userDefaultClip)`
	- [ ] `boolean update(UserDefaultClip userDefaultClip)`
	- [ ] `boolean deleteByKey(int clipId)`
- [ ] Add `UserDefaultClipRepositoryTest` class
- [ ] Extract `UserDefaultClipRepository` interface
- [ ] Add `UserDefaultClipService` class
	- [ ] `Result<UserDefaultClip> add(UserDefaultClip userDefaultClip)`
	- [ ] `Result<UserDefaultClip> update(UserDefaultClip userDefaultClip)`
	- [ ] `boolean deleteByKey(int clipId)`
 - [ ] Add `UserDefaultClipServiceTest` class
 - [ ] Add `UserDefaultClipController` class
	 - [ ] `@PostMapping ResponseEntity<Object> add(@RequestBody UserDefaultClip userDefaultClip)`
	 - [ ] `@PutMapping` `ResponseEntity<Object> update(@RequestBody UserDefaultClip userDefaultClip)`
	 - [ ] `@DeleteMapping("/{clipId}")` `ResponseEntity<Void> deleteByKey(@PathVariable int clipId)`
### Implement Playlist  (Estimate: 2 hours | Actual:  hours)
- [ ] Add `PlaylistJdbcTemplateRepository` class
	- [ ] `List<Playlist> findByUser(int userId)`
	- [ ] `Playlist findById(int playlistId)`
	- [ ] `Playlist add(Playlist playlist)`
	- [ ] `boolean update(Playlist playlist)`
	- [ ] `boolean deleteById(int playlistId)`
- [ ] Add `PlaylistRepositoryTest` class
- [ ] Extract `PlaylistRepository` interface
- [ ] Add `PlaylistService` class
	- [ ] `Result<Playlist> add(Playlist playlist)`
	- [ ] `Result<Playlist> update(Playlist playlist)`
	- [ ] `boolean deleteById(int playlistId)`
 - [ ] Add `PlaylistServiceTest` class
 - [ ] Add `PlaylistController` class
	 - [ ] `@PostMapping ResponseEntity<Object> add(@RequestBody Playlist playlist)`
	 - [ ] `@PutMapping("/{playlistId}")` `ResponseEntity<Object> update(@PathVariable int playlistId, @RequestBody Playlist playlist)`
	 - [ ] `@DeleteMapping("/{playlistId}")` `ResponseEntity<Void> deleteById(@PathVariable int playlistId)`
### Implement PlaylistClip  (Estimate: 2 hours | Actual:  hours)
- [ ] Add `PlaylistClipJdbcTemplateRepository` class
	- [ ] `PlaylistClip add(PlaylistClip playlistClip)`
	- [ ] `boolean update(PlaylistClip playlistClip)`
	- [ ] `boolean deleteByKey(int playlistId, int clipId)`
- [ ] Add `PlaylistClipRepositoryTest` class
- [ ] Extract `PlaylistClipRepository` interface
- [ ] Add `PlaylistClipService` class
	- [ ] `Result<PlaylistClip> add(PlaylistClip playlistClip)`
	- [ ] `Result<PlaylistClip> update(PlaylistClip playlistClip)`
	- [ ] `boolean deleteByKey(int playlistId, int clipId)`
 - [ ] Add `PlaylistClipServiceTest` class
 - [ ] Add `PlaylistClipController` class
	 - [ ] `@PostMapping ResponseEntity<Object> add(@RequestBody PlaylistClip playlistClip)`
	 - [ ] `@PutMapping` `ResponseEntity<Object> update(@RequestBody PlaylistClip playlistClip)`
	 - [ ] `@DeleteMapping("/{playlistId}/{clipId}")` `ResponseEntity<Void> deleteByKey(@PathVariable int playlistId, @PathVariable int clipId)`
### UI Services  (Estimate: 4 hours | Actual:  hours)
- [ ] Add `discordUser.js`
- [ ] Add `discordServer.js`
- [ ] Add `clip.js`
- [ ] Add `userServerClip.js`
- [ ] Add `userDefaultClip.js`
- [ ] Add `playlist.js`
- [ ] Add `playlistClip.js`
### NavBar component  (Estimate: 1 hours | Actual:  hours)
- [ ] Menu toggle button
- [ ] Home Link
- [ ] Search component + search button
- [ ] Sign In / Sign Out
### Menu component  (Estimate: 1 hours | Actual:  hours)
- [ ] Home Link
- [ ] Library Link
- [ ] Playlist Option
- [ ] Server Option
- [ ] Logged In/Logged Out View
- [ ] Hidden (partial/full)
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
### Library page  (Estimate: 2 hours | Actual:  hours)
- [ ] Clips carousel
- [ ] Playlist carousel
- [ ] Server carousel
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
