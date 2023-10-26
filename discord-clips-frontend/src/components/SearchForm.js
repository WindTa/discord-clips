import { useState } from 'react';

import InputGroup from 'react-bootstrap/InputGroup';
import Form from 'react-bootstrap/Form';

import { parseYouTubeId } from '../utilities/youtubeParser';
import { useNavigate } from 'react-router-dom';

function SearchForm() {
    const navigate = useNavigate();

    const [searchText, setSearchText] = useState('');

    const handleSearchChange = (value) => {
        setSearchText(value.currentTarget.value);
    }

    const handleSubmit = () => {
        const youtubeId = parseYouTubeId(searchText);
        navigate(`/clips/add/${youtubeId}`);
    }

    return (
        <Form className='m-auto w-75'>
            <InputGroup className="input-group-lg">
                <Form.Control
                placeholder="Enter YouTube URL"
                aria-label="Search"
                aria-describedby="basic-addon1"
                defaultValue={searchText}
                onChange={handleSearchChange}
                />
                <InputGroup.Text id="basic-addon1" onClick={handleSubmit}>
                    <i className="bi bi-search"></i>
                </InputGroup.Text>
            </InputGroup>
        </Form>
    );
}

export default SearchForm;
