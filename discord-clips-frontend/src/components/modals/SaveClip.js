import { useState, useEffect, useContext } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import AuthContext from '../../contexts/AuthProvider';
import { getPlaylistsByUser, savePlaylist } from '../../services/playlist'

import { Form, InputGroup } from "react-bootstrap";

import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { deleteClipPlaylistById, saveClipPlaylist } from '../../services/clipPlaylist';
import { addNewClip, updateOldClip } from '../../services/clip';

function SaveClip({clip, setClip, clipName, start, duration, playbackRate}) {
    const { auth } = useContext(AuthContext);
    const { youtubeId } = useParams();
    const navigate = useNavigate();

    const [show, setShow] = useState(false);
    const [playlists, setPlaylists] = useState([]);
    const [clipPlaylistsIds, setClipPlaylistsIds] = useState([]);
    const [errors, setErrors] = useState([]);

    const [newPlaylist, setNewPlaylist] = useState('');

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        setClipPlaylistsIds(clip.playlists?.map(c => c.playlist.playlistId));
        getPlaylistsByUser(auth.user?.id)
            .then(setPlaylists)
            .catch(error => {
                console.error(error);
            })
    }, []);

    // Function to save and update
    const addClip = () => {
        // save
        const newClip = {
            clipId: 0,
            clipName: clipName,
            youtubeId: youtubeId,
            startTime: start,
            duration: duration,
            volume: 1.0,
            playbackSpeed: playbackRate,
            discordUserId: auth.user.id,
            playlists: []
        }


		addNewClip(newClip)
			.then(res => {
				if (res.clipId) {
                    setClip(res);
                    navigate(`/library`);
				} else {
					if (res.error) {
						setErrors([res.error]);
					} else {
						setErrors(res);
					}
				}
			})
			.catch(error => {
				console.error(error);
			});

    }

    const updateClip = () => {
        //update
        const updatedClip = {
            clipId: clip.clipId,
            clipName: clipName,
            youtubeId: clip.youtubeId,
            startTime: start,
            duration: duration,
            volume: 1.0,
            playbackSpeed: playbackRate,
            discordUserId: auth.user.id,
            playlists: []
        }

        console.log(updatedClip);
		updateOldClip(updatedClip)
			.then(res => {
				if (!res) {
                    // and then show playlists to let them add to
                    handleShow();
				} else {
					if (res.error) {
						setErrors([res.error]);
					} else {
						setErrors(res);
					}
				}
			})
			.catch(error => {
				console.error(error);
			});
    }

    const addClipToPlaylist = (playlist) => {
        const clipPlaylist = {
            clipId: clip.clipId,
            playlist: playlist,
            displayOrder: 1
        }

        saveClipPlaylist({...clipPlaylist})
            .then(res => {
                console.log(clipPlaylistsIds);
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

    const handlePlaylistNameChange = (value) => {
        setNewPlaylist(value.currentTarget.value);
    }

    const handlePlaylistSubmit = () => {
        const playlist = {
            playlistId: 0,
            playlistName: newPlaylist,
            discordUserId: auth.user.id,
            clips: []
        };

        savePlaylist({...playlist})
            .then(res => {
                setPlaylists([...playlists, res]);
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

    return (
        <>
            <Button className='w-100' onClick={!youtubeId ? updateClip : addClip}>
                <h1>{clip ? 'Save' : 'Update'}</h1>
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>
                        {`Successfully ${!youtubeId ? 'updated!' : 'saved!'}`}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <h5>Save to playlists?</h5>
                    <Form>
                        { playlists && playlists.map((playlist, idx) =>
                            <Form.Check className='fs-4'
                                key={idx}
                                type={'checkbox'}
                                label={playlist.playlistName}
                                defaultChecked={clipPlaylistsIds?.includes(playlist.playlistId)}
                                onChange={(event) => {
                                        event.target.checked 
                                            ? addClipToPlaylist(playlist)
                                            : removeClipFromPlaylist(playlist);
                                    }}
                            />
                        )}
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <InputGroup>
                        <Form.Control className='fs-5' 
                            type='text' 
                            placeholder="Enter New Playlist" 
                            defaultValue={newPlaylist}
                            onChange={handlePlaylistNameChange}
                        >
                        </Form.Control>
                        <InputGroup.Text id="basic-addon1" onClick={handlePlaylistSubmit}>
                            <i class="bi bi-plus-lg"></i>
                        </InputGroup.Text>

                    </InputGroup>
                </Modal.Footer>
            </Modal>
        </>
    );
}
export default SaveClip;
