import { useState } from 'react';
import { useParams } from 'react-router-dom';

import Ratio from 'react-bootstrap/Ratio';
import ReactPlayer from 'react-player';
import MultiRangeSlider from 'multi-range-slider-react';

function ClipEmbed({clip}) {
    const [start, setStart] = useState(clip ? clip.startTime : 0);
    const [end, setEnd] = useState(clip ? (clip.startTime + clip.duration) : 5);
    const [duration, setDuration] = useState(0);

    const handleSliderInput = (e) => {
        setStart(e.minValue);
        setEnd(e.maxValue);
    }

    const handleEmbedDuration = (duration) => {
        setDuration(duration);
    }

    return (
        <div>
            <Ratio aspectRatio="16x9">
                <ReactPlayer 
                    className='h-100 w-100' 
                    url={`https://www.youtube.com/watch?v=${clip?.youtubeId}`} 
                    onDuration={handleEmbedDuration}
                />
            </Ratio>
            <MultiRangeSlider 
                min={0} max={duration} step={0.1}
                minValue={start} maxValue={end}
                ruler='false' barInnerColor='red' 
                style={{border: 'none', boxShadow: 'none', padding: '0px'}}
                onInput={handleSliderInput}
            />
        </div>
    );
}

export default ClipEmbed;
