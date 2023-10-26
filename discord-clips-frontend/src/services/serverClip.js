import { BASE_URL } from './disClipBaseUrl';

const endpointUrl = BASE_URL + '/discord-servers/clips';

export async function saveServerClip(serverClip) {
    const init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(serverClip),
    };

    const response = await fetch(endpointUrl, init);
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

export async function deleteServerClipById(serverId, clipId) {
    const init = {
        method: 'DELETE',
    };

    const response = await fetch(`${endpointUrl}/${serverId}/${clipId}`, init);
    if (response.ok) {
        return null;
    } else if (response.status === 404) {
        return Promise.reject(
            new Error(`The requested resource could not be found.`)
        );
    } else {
        return Promise.reject(
            new Error(`Unexpected status code ${response.status}.`)
        );
    }
}
