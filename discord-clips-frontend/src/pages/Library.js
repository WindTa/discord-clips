import { useContext } from 'react';
import Row from 'react-bootstrap/Row';

import ClipDeck from '../components/decks/ClipDeck';
import PlaylistDeck from '../components/decks/PlaylistDeck';
import ServerDeck from '../components/decks/ServerDeck';

import AuthContext from '../contexts/AuthProvider';

function Library() {
    const { auth } = useContext(AuthContext);

    return (
        <section className='h-100 d-flex flex-column'>
            <Row className='flex-grow-1'>
                <ClipDeck userId={auth.user.id}/>
            </Row>
            <hr/>
            <Row className='flex-grow-1' >
                <PlaylistDeck userId={auth.user.id}/>
            </Row>
            <hr/>
            <Row className='flex-grow-1'>
                <ServerDeck userServers={auth.servers}/>
            </Row>
        </section>
    );
}

export default Library;
