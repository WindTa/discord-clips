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

export async function addNewClip(clip) {
	const init = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(clip, (key, value) => {
            if (typeof value === 'number') {
                return Number(value.toFixed(2));
            }
            return value;
        })
	};

	const response = await fetch(endpointUrl, init);
	if (response.ok) {
		return null;
	} else if (response.status === 400) {
		const errs = await response.json();
		return errs;
	} else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}.`)
		);
	}
}

export async function updateOldClip(clip) {
    const url = `${endpointUrl}/${clip.clipId}`;

	const init = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(clip, (key, value) => {
            if (typeof value === 'number') {
                return Number(value.toFixed(2));
            }
            return value;
        })
	};

	const response = await fetch(url, init);
	if (response.ok) {
		return null;
	} else if (response.status === 400) {
		const errs = await response.json();
		return errs;
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
