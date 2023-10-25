import { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';

import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import AuthContext from '../../contexts/AuthProvider';

function ServerDeck() {
    const { auth } = useContext(AuthContext);

    const [servers, setServers] = useState([]);

    useEffect(() => {
        setServers(auth.servers);
    }, []);

    return (
        <Row xl={6} className='g-4'>
            {servers?.map((server, idx) => (
                <Link to='/server' key={idx}>
                    <Col>
                        <Card>
                            <Card.Img 
                                variant="top" 
                                src={server.icon 
                                    ? `https://cdn.discordapp.com/icons/${server.id}/${server.icon}.png`
                                    : 'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/2890910c-866d-4a01-af6a-2067e1209024/dccpgud-9ab9827e-c2c0-4d5b-a4d9-1021d0e50a9a.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwic3ViIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsImF1ZCI6WyJ1cm46c2VydmljZTpmaWxlLmRvd25sb2FkIl0sIm9iaiI6W1t7InBhdGgiOiIvZi8yODkwOTEwYy04NjZkLTRhMDEtYWY2YS0yMDY3ZTEyMDkwMjQvZGNjcGd1ZC05YWI5ODI3ZS1jMmMwLTRkNWItYTRkOS0xMDIxZDBlNTBhOWEucG5nIn1dXX0.GSMdJ2SZUGRzOTegl01ZzdxCWUMljkcC7b6FJz45w8w'
                            } />
                            <Card.Body>
                                <Card.Title>{server.name}</Card.Title>
                            </Card.Body>
                        </Card>
                    </Col>
                </Link>
            ))}
        </Row>
    );
}

export default ServerDeck;
