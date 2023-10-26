import { Link, useParams} from 'react-router-dom';

import {secondsToMMSS} from '../../utilities/timeConverter';

import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import { useState } from 'react';
import { useEffect } from 'react';

function ClipCard({clip, disabled}) {
    const { serverId } = useParams();

    const thumbnail = (youtubeId) => `https://i.ytimg.com/vi/${youtubeId}/hqdefault.jpg`;
    const [cardDisabled, setCardDisabled] = useState(disabled);

    const handleCheckboxChange = () => {
        setCardDisabled(!cardDisabled);
    }

            //<Card className={disabled ? 'disabled-card' : ''}>
                // <input type="checkbox" 
                //     // checked={!disabled} 
                //     onChange={handleCheckboxChange} 
                //     style={{ position: 'absolute', top: '10px', right: '10px', transform:`scale(2.5)`}} 
                //     />
    return (
        <Col>
            <Card className={(serverId && disabled ? 'disabled-card' : '')}>
                {serverId &&
                    <input type="checkbox" 
                        checked={!disabled} 
                        onChange={handleCheckboxChange} 
                        style={{ position: 'absolute', top: '10px', right: '10px', transform:`scale(2.5)`}} 
                        />
                }
                <Link to={`/clips/edit/${clip.clipId}`}>
                    <Card.Img variant="top" src={thumbnail(clip.youtubeId)} />
                    <Card.Body>
                        <small>{secondsToMMSS(clip.duration)}</small>
                        <Card.Title>{clip.clipName}</Card.Title>
                    </Card.Body>
                </Link>
            </Card>
        </Col>
    );
}

export default ClipCard;
