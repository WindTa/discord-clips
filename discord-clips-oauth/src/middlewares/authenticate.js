const { verify } = require('jsonwebtoken');
const axios = require('axios');

module.exports = async (ctx, next) => {
    const token = ctx.cookies.get('token');
    const discordUsersUrl = "http://localhost:8080/api/discord-users";

    try {
        const { sub } = await verify(token, process.env.JWT_SECRET);
        ctx.state.user = await axios.get(`${discordUsersUrl}/${sub}`).then(response => {return response.data});
    } catch (e) {
        ctx.state.user = null;
    }

    await next();
}
