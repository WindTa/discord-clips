const endpointUrl = (youtubeId) => 
    `https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v=${youtubeId}&format=json`;

export async function getYoutubeJson(youtubeId) {
    const response = await fetch(endpointUrl(youtubeId));
    if (response.ok) {
        return response.json();
    } else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}.`)
		);
	}
}
