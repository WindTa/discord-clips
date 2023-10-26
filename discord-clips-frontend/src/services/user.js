import { BASE_URL } from './disClipBaseUrl';

const endpointUrl = BASE_URL + '/discord-users';

export async function getUserById(userId) {
    const url = `${endpointUrl}/${userId}`
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    } else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}.`)
		);
	}
}

export async function addUser(user) {
    const init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
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

export async function updateUser(user) {
    const url = `${endpointUrl}/${user.discordUserId}`;
    const init = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
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
