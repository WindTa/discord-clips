import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Row from 'react-bootstrap/Row';

import ClipCard from './ClipCard';

import { getClipsByUser } from '../../services/clip';
import { getPlaylistById } from '../../services/playlist';

function ClipDeck({userId, playlistId, serverId}) {
    const [clips, setClips] = useState([]);
    const [playlist, setPlaylist] = useState(null);
    const [server, setServer] = useState(null);
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

        if (playlistId) {
            getPlaylistById(playlistId)
                .then(response => {
                    setPlaylist({playlistId: response.playlistId, playlistName: response.playlistName});
                    setClips(response.clips.map(c => c.clip))
                })
                .catch(error => {
                    console.error(error);
                    navigate('/error', { state: { error } });
                });
        }
    }, [userId, playlistId, serverId]);

    return (
        <div>
            {userId && <h1>Clips</h1>}
            <h1>{playlist?.playlistName}</h1>
            <h1>{server?.serverName}</h1>

            <Row xl={6} className='g-4'>
                {clips?.map((clip, idx) => (
                    <ClipCard clip={clip} key={idx}/>
                ))}
            </Row>
        </div>
    );
}

export default ClipDeck;
