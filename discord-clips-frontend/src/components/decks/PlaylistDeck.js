import { useState, useEffect } from 'react';

import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import { getPlaylistsByUser } from '../../services/playlist';

function PlaylistDeck() {
    const [playlists, setPlaylists] = useState([]);

    useEffect(() => {
        setPlaylists(getPlaylistsByUser);
    }, []);

    // return (
    //     <Row xl={6} className='g-4'>
    //         {playlists?.map((_, idx) => (
    //             <Col key={idx}>
    //                 <Card>
    //                     <Card.Img variant="top" src="../icons/DiscordPlaylistIcon.js" />
    //                     <Card.Body>
    //                         <Card.Title>Card Title</Card.Title>
    //                         <Card.Text>
    //                             This is a longer card with supporting text below as a natural lead-in to additional content. 
    //                             This content is a little bit longer.
    //                         </Card.Text>
    //                     </Card.Body>
    //                 </Card>
    //             </Col>
    //         ))}
    //     </Row>
    // );
    //
    return <div>asdf</div>
}

export default PlaylistDeck;
