import './App.css';

import { Routes, Route } from 'react-router-dom';
import { useEffect, useState, useContext } from 'react';

import Container from 'react-bootstrap/Container';

import Home from './pages/Home';
import Library from './pages/Library';
import Playlist from './pages/Playlist';
import Server from './pages/Server';
import Clip from './pages/Clip';
import NavBar from './components/NavBar';
import SideBar from './components/SideBar';
import AuthContext from './contexts/AuthProvider';

import { getToken, getUser, getServers } from './services/discord';
import { getUserById, addUser, updateUser } from './services/user';
import { addServer, getServerById, updateServer } from './services/server';

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
        Promise.all([getUser(token), getServers(token)])
            .then((results) => {
                setAuth( {token: token, user: results[0], servers: results[1]} );
            })
            .catch((error) => {
                console.error(error);
            })
        return {};
    }

    // Update user, or create if they don't exist
    if (auth.user) {
        const user = auth.user;

        getUserById(user.id)
            .then(() => {
                updateUser({discordUserId: user.id, username: user.username})
                    .then(() => {
                        console.log(`${user.username} has been updated`)
                    })
                    .catch((error) => {
                        console.error(error);
                    })
            })
            .catch(() => {
                addUser({discordUserId: user.id, username: user.username})
                    .then((value) => {
                        console.log(`${value.username} has been added to the database`);
                    })
                    .catch((error) => {
                        console.error(error);
                    })
            })
    }

    // Update servers, or create if they don't exist
    if (auth.servers) {
        const servers = auth.servers;
        for (const server of servers) {
            getServerById(server.id)
                .then(() => {
                    updateServer({discordServerId: server.id, servername: server.name})
                        .then(() => {
                            console.log(`${server.servername} has been updated`)
                        })
                        .catch((error) => {
                            console.error(error);
                        })
                })
                .catch(() => {
                    addServer({discordServerId: server.id, servername: server.name})
                        .then((value) => {
                            console.log(`${value.servername} has been added to the database`);
                        })
                        .catch((error) => {
                            console.error(error);
                        })
                })
        }
    }

    const [collapsed, setCollapsed] = useState(false);
    const handleCollapse = () => {
        setCollapsed(!collapsed);
    }

    return (
        <main style={{ height: 'calc(100vh - 72.5px)' }}>
            <NavBar handleCollapse={handleCollapse}/>
            <div style={{ display: 'flex', height: '100%', overflowY: 'hidden'}}>
                <SideBar collapsed={collapsed}/>
                <Container fluid className='p-5' style={{ overflowY: 'auto' }}>
                    <Routes>
                        <Route path="/" element={<Home />}/>
                        <Route path="/library" element={<Library />}/>
                        <Route path="/playlists/:playlistId" element={<Playlist />}/>
                        <Route path="/servers/:serverId" element={<Server />}/>
                        <Route path="/clips/add/:youtubeId" element={<Clip />}/>
                        <Route path="/clips/edit/:clipId" element={<Clip />}/>
                    </Routes>
                </Container>
            </div>
        </main>
    );

}

export default App;

