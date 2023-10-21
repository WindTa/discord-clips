import './App.css';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

import Home from './pages/Home';
import NavBar from './components/NavBar/NavBar';

function App() {
    const [user, setUser] = useState(null);

    async function getMe() {
        const response = await axios.get('http://localhost:4000/user/me', {
            withCredentials: true
        });

        setUser(response.data);
    }

    useEffect(() => {
        getMe();
    }, []);

    return (
        <Router>
            <header className='my-1'>
                <NavBar user={user}/>
            </header>
            {user && <div>User is logged in</div>}
            <main className='container'>
                <Routes>
                    <Route path="/" element={<Home />}/>
                    <Route path="/menu" element={<div>Menu</div>}/>
                </Routes>
            </main>
        </Router>
    );
}

export default App;
