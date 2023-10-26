export function parseYouTubeId(youtubeLink) {
    const regex = /^(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/(?:embed\/|watch\?v=|v\/)|youtu\.be\/|youtube-nocookie\.com\/(?:embed\/|v\/|watch\?v=))([\w-]{11})(?:\S+)?$/;
    const match = youtubeLink.match(regex);
    return match ? match[1] : null;
}
