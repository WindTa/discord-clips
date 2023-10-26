import InputGroup from 'react-bootstrap/InputGroup';
import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import { useEffect } from 'react';

function SearchForm() {
    const [searchText, setSearchText] = useState('');

    useEffect(() => {
        console.log(searchText);
    }, [searchText])

    const handleSearchChange = (value) => {
        setSearchText(value.currentTarget.value);
    }

    const handleSubmit = () => {
        console.log("submit");
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
