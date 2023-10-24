import './App.css';

import { Routes, Route } from 'react-router-dom';
import { useEffect, useState, useContext } from 'react';
import AuthContext from './contexts/AuthProvider';

import Home from './pages/Home';
import NavBar from './components/NavBar/NavBar';

import { getToken, getUser, getPermissions } from './services/discord';

function App() {
    const { auth, setAuth } = useContext(AuthContext);

    useEffect(() => {
        const code = new URLSearchParams(window.location.search).get('code');
        if (!code) return;

        const token = getToken(code);
        setAuth({ token: token });
    }, []);

    console.log(auth);
    return (
        <main>
            <header className='my-1'>
                <NavBar/>
            </header>
            <div></div>
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/menu" element={<div>Menu</div>}/>
            </Routes>
        </main>
    );
}

export default App;
