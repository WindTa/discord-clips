import { useParams } from 'react-router-dom';

import Row from 'react-bootstrap/Row';

import ClipDeck from '../components/decks/ClipDeck.js';

function Server() {
    const { serverId } = useParams();

    return (
        <section className='h-100 d-flex flex-column'>
            <Row className='flex-grow-1'>
                <ClipDeck serverId={serverId}/>
            </Row>
        </section>
    );
}

export default Server;
