import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import ClipDeck from '../components/decks/ClipDeck';
import PlaylistDeck from '../components/decks/PlaylistDeck';
import ServerDeck from '../components/decks/ServerDeck';

function Library() {
    return (
        <section className='h-100 d-flex flex-column'>
            <Row className='flex-grow-1'>
                <ClipDeck/>
            </Row>
            <Row className='flex-grow-1'>
                <PlaylistDeck/>
            </Row>
            <Row className='flex-grow-1'>
                <ServerDeck/>
            </Row>
        </section>
    );
}

export default Library;
