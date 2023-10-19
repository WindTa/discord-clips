# Setup
- `NOTE`: this is if you want to use this as your own application
## Database
- Execute schema sql scripts
## Maven
- make sure you reload the Maven `pom.xml` file
## Environment Variables
- check `application.properties` and your `environment variables` to make sure everything is correct for both the `main` and `test` applications
## Register Discord Application
- [https://discordapp.com/developers/applications/me](https://discordapp.com/developers/applications/me)
	- Create new application (I named mine `Audio Signature`)
	- Create authentication URL at `http://localhost:8080/login/oauth2/discord`
	- Note your Client ID and Client Secret, save as environment variables
	- [Click here to see an example](../imgs/discord-application.png)