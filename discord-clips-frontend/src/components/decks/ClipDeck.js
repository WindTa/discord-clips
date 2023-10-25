import { useState, useEffect } from 'react';

import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import { getClipsByUser } from '../../services/clip';

function ClipDeck() {
    const [clips, setClips] = useState([]);

    useEffect(() => {
        setClips(getClipsByUser);
    }, []);

    return (
        <Row xl={6} className='g-4'>
            {clips.map((_, idx) => (
                <Col key={idx}>
                    <Card>
                        <Card.Img variant="top" src="../icons/DiscordClipIcon.js" />
                        <Card.Body>
                            <Card.Title>Card Title</Card.Title>
                            <Card.Text>
                                This content is a little bit longer.
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            ))}
        </Row>
    );
}

export default ClipDeck;
