import './App.css';

import { Routes, Route } from 'react-router-dom';
import { useEffect, useState, useContext } from 'react';

import Container from 'react-bootstrap/Container';

import Home from './pages/Home';
import NavBar from './components/NavBar';
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

        Promise.all([getUser(token), getPermissions(token)])
            .then((results) => {
                setAuth( {token: token, user: results[0], permissions: results[1]} );
            })
            .catch((error) => {
                console.error(error);
            })
        return {};
    }

    return (
        <main>
            <header className='my-1'>
                <NavBar/>
            </header>
            <Container fluid>
                <Routes>
                    <Route path="/" element={<Home />}/>
                    <Route path="/menu" element={<div>Menu</div>}/>
                </Routes>
            </Container>
        </main>
    );


}

export default App;
