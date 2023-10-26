import { useState, useEffect, useRef } from 'react';

import Ratio from 'react-bootstrap/Ratio';
import ReactPlayer from 'react-player';
import MultiRangeSlider from 'multi-range-slider-react';

function ClipEmbed({clip}) {
    const playerRef = useRef();
    const [timer, setTimer] = useState(null);
    const [playing, setPlaying] = useState(false);

    const [start, setStart] = useState(clip ? clip.startTime : 0);
    const [duration, setDuration] = useState(clip ? clip.duration : 5);
    const [volume, setVolume] = useState(clip ? clip.volume : 1);
    const [videoLength, setVideoLength] = useState(0);

    // Should replay the video any time there are value changes
    useEffect(() => {
        replay();
    }, [start, duration, volume])

    // Update clip values
    const handleInput = (e) => {
        setStart(e.minValue);
        setDuration(e.maxValue - e.minValue);
    }

    const handleVolume = (e) => {
        console.log(e.volume);
        setVolume(e.volume);
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
        }, duration * 1000);

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
                    url={`https://www.youtube.com/watch?v=${clip?.youtubeId}`} 
                    onDuration={handleVideoLength}
                    onVolumeChange={handleVolume}
                />
            </Ratio>
            <MultiRangeSlider ruler='false' barInnerColor='red' style={{border: 'none', boxShadow: 'none', padding: '0px'}}
                min={0} max={videoLength} step={0.1}
                minValue={start} maxValue={start + duration}
                onInput={handleInput}
            />
            <button onClick={replay}>
                <h1>Replay</h1>
            </button>
        </div>
    );
}

export default ClipEmbed;
