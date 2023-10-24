import './App.css';

import { Routes, Route } from 'react-router-dom';
import { useEffect, useState, useContext } from 'react';

import Container from 'react-bootstrap/Container';

import Home from './pages/Home';
import Library from './pages/Library';
import Playlist from './pages/Playlist';
import Server from './pages/Server';
import NavBar from './components/NavBar';
import SideBar from './components/SideBar';
import AuthContext from './contexts/AuthProvider';

import { getToken, getUser, getPermissions } from './services/discord';

function App() {
    const { auth, setAuth } = useContext(AuthContext);

    useEffect(() => {
        const code = new URLSearchParams(window.location.search).get('code');
        if (!code) return;
        updateAuth(code);
    }, []);

    const updateAuth = async (code) => {
        const token = await getToken(code);

        if (!token) return;
        Promise.all([getUser(token), getPermissions(token)])
            .then((results) => {
                setAuth( {token: token, user: results[0], servers: results[1]} );
            })
            .catch((error) => {
                console.error(error);
            })
        return {};
    }

    const [collapsed, setCollapsed] = useState(false);
    const handleCollapse = () => {
        setCollapsed(!collapsed);
    }

    return (
        <main style={{ height: '100vh' }}>
            <NavBar handleCollapse={handleCollapse}/>
            <div style={{ display: 'flex', height: '100%'}}>
                <SideBar collapsed={collapsed}/>
                <Container fluid>
                    <Routes>
                        <Route path="/" element={<Home />}/>
                        <Route path="/library" element={<Library />}/>
                        <Route path="/playlist" element={<Playlist />}/>
                        <Route path="/server" element={<Server />}/>
                    </Routes>
                </Container>
            </div>
        </main>
    );


}

export default App;

