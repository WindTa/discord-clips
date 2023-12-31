import { BASE_URL } from './disClipBaseUrl';

const endpointUrl = BASE_URL + '/discord-servers';

export async function getServerById(serverId) {
    const url = `${endpointUrl}/${serverId}`;
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    } else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}.`)
		);
	}
}

export async function addServer(server) {
    const init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(server),
    };

    const response = await fetch(endpointUrl, init);
    if (response.ok) {
        return response.json();
    } else if (response.status === 400) {
        const errors = await response.json();
        return errors;
    } else {
        return Promise.reject(
            new Error(`Unexpected status code ${response.status}.`)
        );
    }
}

export async function updateServer(server) {
    const url = `${endpointUrl}/${server.discordServerId}`;
    const init = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(server),
    };

    const response = await fetch(url, init);
    if (response.ok) {
        return null;
    } else if (response.status === 400) {
        const errors = await response.json();
        return errors;
    } else {
        return Promise.reject(
            new Error(`Unexpected status code ${response.status}.`)
        );
    }
}
