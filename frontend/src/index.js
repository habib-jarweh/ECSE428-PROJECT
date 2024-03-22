import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import SignIn from './SignIn/SignIn';
import SignUp from './SignUp/SignUp'; // Ensure SignUp is imported
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} /> // App as the Home Page
        <Route path="/signin" element={<SignIn />} /> // Standalone SignIn Page
        <Route path="/signup" element={<SignUp />} /> // Standalone SignUp Page
        // Add more routes as needed
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
