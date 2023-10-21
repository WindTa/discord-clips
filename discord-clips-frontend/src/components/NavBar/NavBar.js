import Container from 'react-bootstrap/Container';
import { Navbar, Nav } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import DiscordIcon from '../DiscordIcon';

function NavBar({user}) {
    const url = "http://localhost:4000/auth/discord/login";
    const url2 = "http://localhost:4000/auth/discord/logout";

	return (
		<Navbar expand='lg'>
			<Container fluid>
				<LinkContainer to='/menu'>
					<Navbar.Brand>
                        <svg height="24" viewBox="0 0 24 24" width="24" focusable="false" style={{ fill: 'white', pointerEvents: 'none', display: 'block', width: '100%', height: '100%' }}><path d="M21 6H3V5h18v1zm0 5H3v1h18v-1zm0 6H3v1h18v-1z"></path></svg>
                    </Navbar.Brand>
				</LinkContainer>
				<Navbar.Toggle aria-controls='basic-navbar-nav' />
				<Navbar.Collapse id='basic-navbar-nav'>
					<Nav className='me-auto'>
						<LinkContainer to='/' end='true'>
							<Nav.Link>Discord-Clips</Nav.Link>
						</LinkContainer>
					</Nav>
					<Nav className='ml-auto'>
                        <Nav.Link
                            href={!user ? url : url2}
                            className="flex items-center rounded-lg bg-[#5865F2] text-white">
                            <DiscordIcon />
                            <span>Sign {!user ? "in with " : "out of "} Discord</span>
                        </Nav.Link>
					</Nav>
				</Navbar.Collapse>
			</Container>
		</Navbar>
	);
}

export default NavBar;
