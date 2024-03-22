// SignIn.js
import React, { useState } from 'react';
import axios from 'axios';
import logo from './logo.svg';
import './App.css';

function SignIn() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/api/login', { email, password });
      console.log(response.data); // Assuming the backend returns some data upon successful login
      // Add logic to handle successful login (e.g., redirect to dashboard)
    } catch (error) {
      console.error('Error signing in:', error);
      // Add logic to handle login failure (e.g., display error message to the user)
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h2 style={{ color: 'white' }}>Sign In</h2>
        <form onSubmit={handleSubmit}>
          <label htmlFor="email" style={{ color: 'white' }}>Email:</label>
          <input type="email" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          <label htmlFor="password" style={{ color: 'white' }}>Password:</label>
          <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          <button type="submit">Sign In</button>
        </form>
      </header>
    </div>
  );
}

export default SignIn;
