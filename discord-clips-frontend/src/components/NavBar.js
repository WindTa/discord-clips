import Container from 'react-bootstrap/Container';
import { Navbar, Nav } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import NavAuth from './NavAuth';

function NavBar() {
	return (
		<Navbar expand='lg'>
			<Container>
				<LinkContainer to='/'>
					<Navbar.Brand>discord-clips</Navbar.Brand>
				</LinkContainer>
				<Navbar.Toggle aria-controls='basic-navbar-nav' />
				<Navbar.Collapse id='basic-navbar-nav'>
					<Nav className='me-auto'>
						<LinkContainer to='/' end='true'>
							<Nav.Link>Home</Nav.Link>
						</LinkContainer>
					</Nav>
					<Nav className='me-auto'>
                        <NavAuth />
					</Nav>
				</Navbar.Collapse>
			</Container>
		</Navbar>
	);
}

export default NavBar;
