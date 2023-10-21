require('dotenv').config();

const Koa = require('koa');
const Router = require('@koa/router');
const cors = require('@koa/cors');
const bodyParser = require('koa-bodyparser');
const axios = require('axios');
const { sign } = require('jsonwebtoken');
const authenticate = require('./middlewares/authenticate');

const app = new Koa();
const router = new Router();

app.use(
    cors({
        credentials: true
    })
);
app.use(bodyParser());
app.use(router.routes());
app.use(router.allowedMethods());

router.use(authenticate);

router.get('/user/me', async ctx => {
    ctx.body = ctx.state.user;
});

router.get('/auth/discord/login', async ctx => {
    const url = 'https://discord.com/api/oauth2/authorize?client_id=1163916171846877325&redirect_uri=http%3A%2F%2Flocalhost%3A4000%2Fauth%2Fdiscord%2Fcallback&response_type=code&scope=identify%20guilds';
    ctx.redirect(url);
});

router.get('/auth/discord/logout', async ctx => {
    ctx.cookies.set('token', null);
    ctx.redirect(process.env.CLIENT_REDIRECT_URL)
});

router.get('/auth/discord/callback', async ctx => {
    if (!ctx.query.code) throw new Error('Code not provided');

    const { code } = ctx.query;
    const params = new URLSearchParams({
        client_id: process.env.DISCORD_CLIENT_ID,
        client_secret: process.env.DISCORD_CLIENT_SECRET,
        grant_type: 'authorization_code',
        code,
        redirect_uri: process.env.DISCORD_REDIRECT_URI
    });

    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Accept-Encoding': 'application/x-www-form-urlencoded'
    };

    const response = await axios.post(
        'https://discord.com/api/oauth2/token', 
        params,
        {
            headers
        }
    );

    const userResponse = await axios.get('https://discord.com/api/users/@me', {
        headers: {
            Authorization: `Bearer ${response.data.access_token}`,
            ...headers
        }
    });

    // const guildsResponse = await axios.get('https://discord.com/api/users/@me/guilds', {
    //     headers: {
    //         Authorization: `Bearer ${response.data.access_token}`,
    //         ...headers
    //     }
    // });

    const { id, global_name } = userResponse.data;
    const discordUsersUrl = "http://localhost:8080/api/discord-users";

    const userExists = await axios.get(`${discordUsersUrl}/${id}`, { validateStatus: false })
        .then(response => {
            return response.status === 200;
        });

    if (userExists) {
        axios.put(`${discordUsersUrl}/${id}`, {
            discordUserId: id,
            username: global_name
        });
    } else {
        axios.post(`${discordUsersUrl}`, {
            discordUserId: id,
            username: global_name
        });
    }

    const token = await sign( {sub: id}, process.env.JWT_SECRET, {
        expiresIn: '1h'
    });

    ctx.cookies.set('token', token);
    ctx.redirect(process.env.CLIENT_REDIRECT_URL);
});

app.listen(4000);
