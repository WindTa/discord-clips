import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import NavBar from './components/NavBar';
import Home from './pages/Home';

function App() {
    return (
        <Router>
			<header className='my-3'>
				<NavBar />
			</header>
            <main className='container'>
                <Routes>
                    <Route path="/" element={<Home />}/>
                </Routes>
            </main>
        </Router>
    );
}

export default App;
