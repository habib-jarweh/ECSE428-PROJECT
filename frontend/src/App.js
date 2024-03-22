import React from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import SignIn from './SignIn/SignIn';
import SignUp from './SignUp/SignUp'; // Make sure this import is correct
import logo from './logo.png';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>SmartEats Application</p>
        <nav>
            <ul>
              <li><Link to="/signin" className="App-link">Sign In</Link></li>
              <li><Link to="/signup" className="App-link">Sign Up</Link></li> {/* Add this line */}
            </ul>
        </nav>
        <Routes>
            <Route path="/signin" element={<SignIn />} />
            <Route path="/signup" element={<SignUp />} /> {/* Add this route */}
        </Routes>
      </header>
    </div>
  );
}

export default App;
