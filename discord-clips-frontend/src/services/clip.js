import { BASE_URL } from './disClipBaseUrl';

const endpointUrl = BASE_URL + '/clips';

export async function getClipsByUser(userId) {
    const url = `${endpointUrl}/discord-user/${userId}`;
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    } else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}.`)
		);
	}
}

export async function getClipById(clipId) {
    const url = `${endpointUrl}/${clipId}`;
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    } else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}.`)
		);
	}
}

export async function deleteClipById(clipId) {
    const init = {
        method: 'DELETE',
    };

    const url = `${endpointUrl}/${clipId}`;
    const response = await fetch(url, init);
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
