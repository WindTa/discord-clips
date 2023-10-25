import { BASE_URL } from './disClipBaseUrl';

import AuthContext from '../contexts/AuthProvider';

const endpointUrl = BASE_URL + '/playlists';

export async function getPlaylistsByUser(userId) {
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
