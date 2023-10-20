import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './pages/Home';
import NavBar from './components/NavBar/NavBar';

function App() {
    return (
        <Router>
            <header className='my-1'>
                <NavBar />
            </header>
            <main className='container'>
                <Routes>
                    <Route path="/" element={<Home />}/>
                    <Route path="/menu" element={<div>Menu</div>}/>
                    <Route path="/signin" element={<div>Sign In</div>}/>
                </Routes>
            </main>
        </Router>
    );
}

export default App;
