import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import ClipEmbed from '../components/clips/ClipEmbed';

import { getClipById } from '../services/clip';

function Clip() {
    const { clipId, youtubeId } = useParams();
    const [ clip, setClip ] = useState(null);

    useEffect(() => {
        if (youtubeId) {
            setClip({youtubeId: youtubeId});
            return;
        }

        getClipById(clipId)
            .then(response => {
                setClip(response);
            })
            .catch(error => {
                console.error(error);
                setClip(null);
            });
    }, [])

    return (
        <section className='h-100 d-flex flex-column'>
            {clip && (
                <Row className='h-100'>
                    <Col md={10}>
                        <ClipEmbed clip={clip} />
                    </Col>
                </Row>
            )}
        </section>
    );
}

export default Clip;
