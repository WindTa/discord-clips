import { useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../../contexts/AuthProvider';
import { getPlaylistsByUser } from '../../services/playlist'


import { Form } from "react-bootstrap";

import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { deleteClipPlaylistById, saveClipPlaylist } from '../../services/clipPlaylist';

function SaveClip({clip, start, duration, playbackRate}) {
    const { auth } = useContext(AuthContext);
    const navigate = useNavigate();

    const [show, setShow] = useState(false);
    const [playlists, setPlaylists] = useState([]);
    const [clipPlaylistsIds, setClipPlaylistsIds] = useState([]);
    const [errors, setErrors] = useState([]);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        setClipPlaylistsIds(clip.playlists?.map(c => c.playlist.playlistId));
        getPlaylistsByUser(auth.user.id)
            .then(setPlaylists)
            .catch(error => {
                console.error(error);
            })
    }, []);

    // Function to save and update
    const saveClip = () => {
        // save
        // and then show playlists to let them add to
        handleShow();
    }

    const updateClip = () => {

    }

    const addClipToPlaylist = (playlist) => {
        const clipPlaylist = {
            clipId: clip.clipId,
            playlist: playlist,
            displayOrder: 1
        }

        saveClipPlaylist({...clipPlaylist})
            .then(res => {
                setClipPlaylistsIds([...clipPlaylistsIds, playlist.playlistId]);
                if (res.error) {
                    setErrors([res.error]);
                } else {
                    setErrors(res);
                }
            })
            .catch(error => {
                console.error(error);
            });
    }

    const removeClipFromPlaylist = (playlist) => {
        deleteClipPlaylistById(clip.clipId, playlist.playlistId)
            .then(response => {
                setClipPlaylistsIds(clipPlaylistsIds.filter(item => item != playlist.playlistId));
            })
            .catch(error => {
                console.error(error);
            });
    }

    return (
        <>
            <Button className='w-100' onClick={clip ? saveClip : updateClip}>
                <h1>{clip ? 'Save' : 'Update'}</h1>
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>
                        {`Successfully ${clip ? 'updated!' : 'saved!'}`}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <h5>Save to playlists?</h5>
                    <Form>
                        { playlists && playlists.map((playlist, idx) =>
                            <Form.Check
                                key={idx}
                                type={'checkbox'}
                                label={playlist.playlistName}
                                defaultChecked={clipPlaylistsIds.includes(playlist.playlistId)}
                                onChange={(event) => {
                                        event.target.checked 
                                            ? addClipToPlaylist(playlist)
                                            : removeClipFromPlaylist(playlist);
                                    }}
                            />
                        )}
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
}
export default SaveClip;
