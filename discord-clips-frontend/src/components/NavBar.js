import Container from 'react-bootstrap/Container';

import { useContext } from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import AuthContext from '../contexts/AuthProvider';

import MenuIcon from './icons/MenuIcon';
import SearchForm from './SearchForm';
import DiscordClipIcon from './icons/DiscordClipIcon';
import DiscordIcon from './icons/DiscordIcon';
import AvatarIcon from './icons/AvatarIcon';

function NavBar() {
    const { auth, setAuth } = useContext(AuthContext);

    const oauth = "https://discord.com/api/oauth2/authorize?client_id=1163916171846877325&redirect_uri=http%3A%2F%2Flocalhost%3A3000&response_type=code&scope=identify%20guilds"
    const home = "/";

    const loggedOut = Object.keys(auth).length === 0;

    console.log(auth);
	return (
		<Navbar expand='lg'>
			<Container fluid>
				<LinkContainer to='/'>
					<Navbar.Brand>
                        <DiscordClipIcon />
                        DisClip
                    </Navbar.Brand>
				</LinkContainer>
				<Navbar.Toggle aria-controls='basic-navbar-nav' />
				<Navbar.Collapse id='basic-navbar-nav'>
                    <SearchForm />
					<Nav className>
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
