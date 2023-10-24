import InputGroup from 'react-bootstrap/InputGroup';
import Form from 'react-bootstrap/Form';

function SearchForm() {

    return (
        <Form className='m-auto w-75'>
            <InputGroup className="input-group-lg">
                <Form.Control
                placeholder="Enter YouTube URL"
                aria-label="Search"
                aria-describedby="basic-addon1"
                />
                <InputGroup.Text id="basic-addon1">
                    <i className="bi bi-search"></i>
                </InputGroup.Text>
            </InputGroup>
        </Form>
    );
}

export default SearchForm;
