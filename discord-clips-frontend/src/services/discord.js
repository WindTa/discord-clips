import axios from 'axios';

const BASE_URL = "https://discord.com/api";
const headers = {
    'Content-Type': 'application/x-www-form-urlencoded',
};

export async function getToken(code) {
    const TOKEN_URL = "/oauth2/token";
    const params = new URLSearchParams({
        client_id: process.env.REACT_APP_DISCORD_CLIENT_ID,
        client_secret: process.env.REACT_APP_DISCORD_CLIENT_SECRET,
        grant_type: 'authorization_code',
        code,
        redirect_uri: process.env.REACT_APP_DISCORD_REDIRECT_URI
    });

    const response = await axios.post(`${BASE_URL}${TOKEN_URL}`,
        params,
        {
            headers
        }
    ).catch((error) => {
            console.error(error);
    });

    return response?.data?.access_token;
}

export async function getUser(token) {
    const USER_URL = "/users/@me";
    const response = await axios.get(`${BASE_URL}${USER_URL}`, {
        headers: {
            Authorization: `Bearer ${token}`,
            ...headers
        }
    });

    return { id: response.data?.id, username: response.data?.username, avatar: response.data?.avatar };
}

export async function getPermissions(token) {
    const USER_GUILDS_URL = "/users/@me/guilds";
    const response = await axios.get(`${BASE_URL}${USER_GUILDS_URL}`, {
        headers: {
            Authorization: `Bearer ${token}`,
            ...headers
        }
    });

    return response?.data?.map((guild) => {
        return { id: guild.id, name: guild.name, owner: guild.owner, icon: guild.icon};
    });
}
