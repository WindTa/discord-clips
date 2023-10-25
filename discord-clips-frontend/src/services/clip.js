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
