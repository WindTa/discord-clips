import { Link} from 'react-router-dom';

import {secondsToMMSS} from '../../utilities/timeConverter';

import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';

function ClipCard({clip}) {
    const thumbnail = (youtubeId) => `https://i.ytimg.com/vi/${youtubeId}/hqdefault.jpg`;

    return (
        <Link to={`/clips/edit/${clip.clipId}`}>
            <Col>
                <Card>
                    <Card.Img variant="top" src={thumbnail(clip.youtubeId)} />
                    <Card.Body>
                        <small>{secondsToMMSS(clip.duration)}</small>
                        <Card.Title>{clip.clipName}</Card.Title>
                    </Card.Body>
                </Card>
            </Col>
        </Link>
    );
}

export default ClipCard;
