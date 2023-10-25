import { useParams } from 'react-router-dom';

import Row from 'react-bootstrap/Row';

import ClipDeck from '../components/decks/ClipDeck.js';

function Playlist() {
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
