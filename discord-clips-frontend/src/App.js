import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useEffect, useState } from 'react';

import Home from './pages/Home';
import NavBar from './components/NavBar/NavBar';
import UserContext from './context/UserContext';

import supabaseClient from './services/SupabaseClient';

function App() {
    const [user, setUser] = useState(null);
    const [session, setSession] = useState(null);

    useEffect(() => {
        async function getUserData() {
            await supabaseClient.auth.getUser().then((value) => {
                if (value.data?.user) {
                    setUser(value);
                }
            })
        }
        getUserData();
    }, [])

    useEffect(() => {
        async function getSessionData() {
            await supabaseClient.auth.getSession().then((value) => {
                if (value.data?.session) {
                    setSession(value);
                }
            })
        }
        getSessionData();
    }, [])

    async function signout() {
        const { error } = await supabaseClient.auth.signOut();
        setUser(null);
    }

    const auth = {
        user: user,
        session: session,
        signout
    }

    console.log(user);

    return (
        <UserContext.Provider value={auth}>
            <Router>
                <header className='my-1'>
                    <NavBar />
                </header>
                <main className='container'>
                    <Routes>
                        <Route path="/" element={<Home />}/>
                    </Routes>
                </main>
            </Router>
        </UserContext.Provider>
    );
}

export default App;
