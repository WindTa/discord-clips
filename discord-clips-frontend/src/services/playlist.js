import { BASE_URL } from './disClipBaseUrl';

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

export async function getPlaylistsWithThumbnail(userId) {
    return getPlaylistsByUser(userId)
        .then(response => { 
            return response.map(getPlaylistWithThumbnail);
        })
        .catch(error => {
            console.error(error);
        })
}

async function getPlaylistWithThumbnail(playlist) {
    return getPlaylistById(playlist.playlistId)
        .catch(error => {
            console.error(error);
        })
}

export async function getPlaylistById(playlistId) {
    const url = `${endpointUrl}/${playlistId}`;
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    } else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}.`)
		);
	}
}
