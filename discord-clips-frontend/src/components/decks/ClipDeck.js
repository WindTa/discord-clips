import { useState, useEffect, useContext } from 'react';
import AuthContext from '../../contexts/AuthProvider';

import Row from 'react-bootstrap/Row';

import ClipCard from './ClipCard';

import { getClipsByUser } from '../../services/clip';
import { getPlaylistById } from '../../services/playlist';
import { getServerById } from '../../services/server';

function ClipDeck({userId, playlistId, serverId}) {
    const { auth } = useContext(AuthContext);

    const [clips, setClips] = useState([]);
    const [playlist, setPlaylist] = useState(null);
    const [server, setServer] = useState(null);
    const [serverClips, setServerClips] = useState([]);

    useEffect(() => {
        if (userId && !serverId) {
            getClipsByUser(userId)
                .then(setClips)
                .catch(error => {
                    console.error(error);
                    setClips([]);
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
                    setPlaylist(null);
                    setClips([]);
                });
        }

        if (serverId) {
            getClipsByUser(auth.user.id)
                .then(setClips)
                .catch(error => {
                    console.error(error);
                    setClips([]);
                });

            getServerById(serverId)
                .then(response => {
                    // This is only temporary until I fix the BigDecimal issue
                    setServer({serverId: response.serverId, serverName: response.servername});
                    setServerClips(response.clips
                        .map(c => c.clip)
                        .filter(c => c.discordUserId === Math.round(auth.user.id)));
                })
                .catch(error => {
                    console.error(error);
                    setServer(null);
                    setClips([]);
                    setServerClips([]);
                });
        }

    }, [userId, playlistId, serverId]);

    return (
        <div>
            {(userId && !serverId) && <h1>Clips</h1>}
            <h1>{playlist?.playlistName}</h1>
            <h1>{server?.serverName}</h1>

            <Row xl={6} className='g-4'>
                {clips?.map((clip, idx) => {
                    if (serverId) {
                        const disabled = !serverClips.map(serverClip => serverClip.clipId).includes(clip.clipId);
                        return (<ClipCard clip={clip} key={idx} disabled={disabled} serverId={serverId}/>)
                    } else {
                        return (<ClipCard clip={clip} key={idx} serverId={serverId}/>)
                    }
                })}
            </Row>
        </div>
    );
}

export default ClipDeck;
