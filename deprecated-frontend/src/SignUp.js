import React, { useState } from 'react';
import axios from 'axios'; // Import Axios library
import logo from './logo.svg';
import './App.css';

function SignUp() {
  // Define state variables to store form data
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  // Function to handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default form submission behavior
    try {
      // Send a POST request to the API endpoint with form data
      const response = await axios.post('/api/signup', { name, email, password });
      console.log(response.data); // Log response data to the console
      // Add logic to handle successful sign-up (e.g., redirect to login page)
    } catch (error) {
      console.error('Error signing up:', error); // Log any errors to the console
      // Add logic to handle sign-up failure (e.g., display error message to the user)
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h2 style={{ color: 'white' }}>Sign Up</h2>
        <form onSubmit={handleSubmit}> {/* Call handleSubmit function on form submission */}
          <label htmlFor="name" style={{ color: 'white' }}>Name:</label>
          <input type="text" id="name" name="name" value={name} onChange={(e) => setName(e.target.value)} />
          <label htmlFor="email" style={{ color: 'white' }}>Email:</label>
          <input type="email" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          <label htmlFor="password" style={{ color: 'white' }}>Password:</label>
          <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          <button type="submit">Sign Up</button>
        </form>
      </header>
    </div>
  );
}

export default SignUp;
