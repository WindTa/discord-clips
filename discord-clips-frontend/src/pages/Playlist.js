import { useContext } from 'react';
import { useParams } from 'react-router-dom';

import Row from 'react-bootstrap/Row';

import ClipDeck from '../components/decks/ClipDeck.js';
import AuthContext from '../contexts/AuthProvider.js';

function Playlist() {
    const { auth } = useContext(AuthContext);

    const { playlistId } = useParams();

    return (
        <section className='h-100 d-flex flex-column'>
            <Row className='flex-grow-1'>
                <ClipDeck playlistId={playlistId}/>
            </Row>
        </section>
    );
}

export default Playlist;
