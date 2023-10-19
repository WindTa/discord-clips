import Container from 'react-bootstrap/Container';
import { Navbar, Nav } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { useContext } from 'react';

import UserContext from '../../context/UserContext';
import NavLogin from './NavLogin';
import NavLogout from './NavLogout';

function NavBar() {
    const { user } = useContext(UserContext);

	return (
		<Navbar expand='lg'>
			<Container fluid>
				<LinkContainer to='/'>
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
                    { user && <img className="circle-crop" src={user?.data.user.user_metadata.avatar_url}/> }
					<Nav className='ml-auto'>
                        {user 
                            ? <NavLogout />
                            : <NavLogin />
                        }
					</Nav>
				</Navbar.Collapse>
			</Container>
		</Navbar>
	);
}

export default NavBar;
