import Container from 'react-bootstrap/Container';

import { useContext } from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import AuthContext from '../contexts/AuthProvider';

import DiscordIcon from './DiscordIcon';

function NavBar() {
    const { auth, setAuth } = useContext(AuthContext);

    const oauth = "https://discord.com/api/oauth2/authorize?client_id=1163916171846877325&redirect_uri=http%3A%2F%2Flocalhost%3A3000&response_type=code&scope=identify%20guilds"
    const home = "/";

    const loggedOut = Object.keys(auth).length === 0;

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
                            // href={url}
                            href={loggedOut ? oauth : home}
                            className="flex items-center rounded-lg bg-[#5865F2] text-white">
                            <DiscordIcon />
                            <span>Sign {loggedOut ? "in with" : "out of"} Discord</span>
                        </Nav.Link>
					</Nav>
				</Navbar.Collapse>
			</Container>
		</Navbar>
	);
}

export default NavBar;
