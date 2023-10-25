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
