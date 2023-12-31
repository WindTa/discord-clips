import { useState, useEffect, useContext } from 'react';
import { getPlaylistsByUser } from '../../services/playlist'

import { Link, useNavigate } from 'react-router-dom';

import { MenuItem, SubMenu } from 'react-pro-sidebar';

import AuthContext from '../../contexts/AuthProvider';

function PlaylistSubMenu({isActive, setActive}) {
    const { auth } = useContext(AuthContext);
	const [playlists, setPlaylists] = useState([]);
    const navigate = useNavigate();

    const icon = <i className="bi bi-music-note-list"></i>

    useEffect(() => {
        getPlaylistsByUser(auth.user.id)
            .then(setPlaylists)
            .catch(error => {
                console.error(error);
                navigate('/error', { state: { error } });
            })
    }, []);

    return (
        <SubMenu 
            label={"Playlists"}
            icon={icon}
        >
            {playlists.map((playlist, idx) => {
                return (
                    <MenuItem 
                        active={isActive(`playlist-${idx}`)}
                        onClick={() => setActive(`playlist-${idx}`)}
                        key={idx} icon={icon}
                        component={ <Link to={`/playlists/${playlist.playlistId}`} /> }
                    >
                        {playlist.playlistName}
                    </MenuItem>
                );
            })}
        </SubMenu>
    );
}
export default PlaylistSubMenu;


