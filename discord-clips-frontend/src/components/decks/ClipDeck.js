import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Row from 'react-bootstrap/Row';

import ClipCard from './ClipCard';

import { getClipsByUser } from '../../services/clip';

function ClipDeck({userId, playlistId, serverId}) {
    const [clips, setClips] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        if (userId) {
            getClipsByUser(userId)
                .then(setClips)
                .catch(error => {
                    console.error(error);
                    navigate('/error', { state: { error } });
                });
        }
    }, []);

    return (
        <div>
            <h1>Clips</h1>

            <Row xl={6} className='g-4'>
                {clips?.map((clip, idx) => (
                    <ClipCard clip={clip} key={idx}/>
                ))}
            </Row>
        </div>
    );
}

export default ClipDeck;
