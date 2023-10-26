import { useState, useEffect, useRef } from 'react';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Button from 'react-bootstrap/Button';
import Ratio from 'react-bootstrap/Ratio';
import ReactPlayer from 'react-player';
import MultiRangeSlider from 'multi-range-slider-react';

import SaveClip from '../modals/SaveClip';
import DeleteClip from '../modals/DeleteClip';
import { Form } from 'react-bootstrap';
import { useParams } from 'react-router-dom';

function ClipEmbed({clip, setClip}) {
    const playerRef = useRef();
    const [playing, setPlaying] = useState(false);
    const [timer, setTimer] = useState(null);

    const {youtubeId} = useParams();

    const [clipName, setClipName] = useState(!youtubeId ? clip.clipName : '');
    const [start, setStart] = useState(!youtubeId ? clip.startTime : 0);
    const [duration, setDuration] = useState(!youtubeId? clip.duration : 5);
    const [playbackRate, setPlaybackRate] = useState(!youtubeId ? clip.playbackSpeed : 1.0);

    const [videoLength, setVideoLength] = useState(0);

    // Should replay the video any time there are value changes
    useEffect(() => {
        replay();
    }, [start, duration, playbackRate])

    // Update clip values
    const handleClipNameChange = (value) => {
        setClipName(value.currentTarget.value);
    }

    const handleInput = (e) => {
        setStart(e.minValue);
        setDuration(e.maxValue - e.minValue);
    }

    const handlePlaybackRate = (e) => {
        setPlaybackRate(e);
    }

    // Handle range slider end value
    const handleVideoLength = (videoLength) => {
        setVideoLength(videoLength);
    }

    // Function to replay clip
    const replay = () => {
        clearTimeout(timer);
        playerRef.current.seekTo(start, 'seconds');
        setPlaying(true);
        const newTimer = setTimeout(() => {
            setPlaying(false);
        }, (duration * 1000 / playbackRate));

        setTimer(newTimer);
    }

    return (
        <div>
            <Ratio aspectRatio="16x9">
                <ReactPlayer 
                    ref={playerRef}
                    className='h-100 w-100' 
                    controls="true"
                    playing={playing}
                    playbackRate={playbackRate}
                    url={`https://www.youtube.com/watch?v=${clip?.youtubeId}`} 
                    onDuration={handleVideoLength}
                    onPlaybackRateChange={handlePlaybackRate}
                />
            </Ratio>
            <MultiRangeSlider label='false' ruler='false' barInnerColor='red' style={{border: 'none', boxShadow: 'none', padding: '0px 0px 10px 0px'}}
                min={0} max={videoLength} step={0.1}
                minValue={start} maxValue={start + duration}
                onInput={handleInput}
            />
            <Row>
                <Col md={5}>
                    <Form>
                        <Form.Control className='fs-1' 
                            type='text' 
                            placeholder="Enter Clip Title" 
                            defaultValue={clipName}
                            onChange={handleClipNameChange}
                        ></Form.Control>
                    </Form>
                </Col>
                <Col>
                    <Button className='w-100' onClick={replay}>
                        <h1>Replay</h1>
                    </Button>
                </Col>
                <Col>
                    <SaveClip clip={clip} setClip={setClip} clipName={clipName} start={start} duration={duration} playbackRate={playbackRate}/>
                </Col>
                <Col>
                    <DeleteClip clipId={clip.clipId} />
                </Col>
            </Row>
        </div>
    );
}

export default ClipEmbed;
