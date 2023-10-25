import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Row from 'react-bootstrap/Row';

import PlaylistCard from './PlaylistCard';

import { getPlaylistsWithThumbnail } from '../../services/playlist';

function PlaylistDeck({userId}) {
    const [playlists, setPlaylists] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getPlaylistsWithThumbnail(userId)
            .then(response => {
                Promise.all(response).then(setPlaylists);
            })
            .catch(error => {
                console.error(error);
                navigate('/error', { state: { error } });
            })
    }, []);

    return (
        <div>
            <h1>Playlists</h1>

            <Row xl={6} className='g-4'>
                {playlists?.map((playlist, idx) => (
                    <PlaylistCard playlist={playlist} key={idx} />
                ))}
            </Row>
        </div>
    );
}

export default PlaylistDeck;
