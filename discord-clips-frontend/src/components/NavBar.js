import Container from 'react-bootstrap/Container';

import { useContext } from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import Button from 'react-bootstrap/Button';

import AuthContext from '../contexts/AuthProvider';

import MenuIcon from './icons/MenuIcon';
import SearchForm from './SearchForm';
import DiscordClipIcon from './icons/DiscordClipIcon';
import DiscordIcon from './icons/DiscordIcon';
import AvatarIcon from './icons/AvatarIcon';

function NavBar({handleCollapse}) {
    const { auth, setAuth } = useContext(AuthContext);

    const oauth = process.env.REACT_APP_DISCORD_GENERATED_URL;
    const home = "/";

    const loggedOut = Object.keys(auth).length === 0;

	return (
		<Navbar fixed="top" sticky="top" expand='lg' style={{backgroundColor: '#1A1A1A'}}>
			<Container fluid>
                <Button onClick={handleCollapse} variant='none' style={{border: 'none'}}>
                    <i className="bi bi-list fs-3"></i>
                </Button>
				<LinkContainer to='/'>
					<Navbar.Brand>
                        <DiscordClipIcon />
                        DisClip
                    </Navbar.Brand>
				</LinkContainer>
				<Navbar.Toggle aria-controls='basic-navbar-nav' />
				<Navbar.Collapse id='basic-navbar-nav'>
                    <SearchForm />
					<Nav>
                        <Nav.Link
                            href={loggedOut ? oauth : home}
                            className="flex items-center rounded-lg bg-[#5865F2] text-white fs-5">
                            {loggedOut ? <DiscordIcon /> : <AvatarIcon userId={auth.user.id} avatarId={auth.user.avatar} /> }
                            {loggedOut ? "Sign In" : "Sign Out"}
                        </Nav.Link>
					</Nav>
				</Navbar.Collapse>
			</Container>
		</Navbar>
	);
}

export default NavBar;
