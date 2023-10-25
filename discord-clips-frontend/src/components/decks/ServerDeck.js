import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import AuthContext from '../../contexts/AuthProvider';

import Row from 'react-bootstrap/Row';

import ServerCard from './ServerCard';

function ServerDeck({userServers}) {
    const [servers, setServers] = useState([]);

    useEffect(() => {
        setServers(userServers);
    }, []);

    return (
        <div>
            <h1>Servers</h1>

            <Row sm={1} xl={6} className='g-4'>
                {servers?.map((server, idx) => (
                    <ServerCard server={server} key={idx}/>
                ))}
            </Row>
        </div>
    );
}

export default ServerDeck;
