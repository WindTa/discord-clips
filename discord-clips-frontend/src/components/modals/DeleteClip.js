import { useState } from 'react';
import { Modal } from 'react-bootstrap';

import Button from "react-bootstrap/Button";
import { useNavigate, useParams } from 'react-router-dom';
import { deleteClipById } from '../../services/clip';

function DeleteClip({clipId}) {
    const navigate = useNavigate();
    const { youtubeId } = useParams();

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    // Function to save and update
    const deleteClip = () => {
        deleteClipById(clipId)
            .then(() => {
                navigate('/');
            })
            .catch(error => {
                console.error(error);
            })
        handleClose();
    }

    return (
        <>
            <Button variant="danger" className='w-100' onClick={handleShow} disabled={youtubeId}>
                <h1>Delete</h1>
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Delete?</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Are you sure you want to delete?
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="danger" onClick={() => deleteClip()}>Delete</Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default DeleteClip;
