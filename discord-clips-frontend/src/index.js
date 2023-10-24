import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import { AuthProvider } from './contexts/AuthProvider';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Router>
            <AuthProvider>
                <Routes>
                    <Route path="/*" element=<App/> />
                </Routes>
            </AuthProvider>
        </Router>
    </React.StrictMode>
);
