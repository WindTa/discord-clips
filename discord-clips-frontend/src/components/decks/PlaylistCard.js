import { Link} from 'react-router-dom';

import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';

function PlaylistCard({playlist}) {
    const thumbnail = (youtubeId) => `https://i.ytimg.com/vi/${youtubeId}/hqdefault.jpg`;

    return (
        <Link to={`/playlists/${playlist.playlistId}`}>
            <Col>
                <Card>
                    <Card.Img variant="top" src={thumbnail(playlist.clips[0]?.clip.youtubeId)} />
                    <Card.Body>
                        <small>{`${playlist.clips.length} video(s)`}</small>
                        <Card.Title>{playlist.playlistName}</Card.Title>
                    </Card.Body>
                </Card>
            </Col>
        </Link>
    );
}

export default PlaylistCard;
