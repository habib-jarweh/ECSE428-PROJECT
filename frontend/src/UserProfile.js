// UserProfile.js
import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

function UserProfile() {
  const [username, setUsername] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [address, setAddress] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const handleUpdate = async (e) => {
    e.preventDefault();
    // Here you would typically send the updated information to the backend
    console.log('Updated information:', { username, name, email, address, phoneNumber });
    // Add logic to handle the response, such as displaying a success message or handling errors
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" onClick={() => {/* Logic to navigate back to User Dashboard */}} />
        <h2 style={{ color: 'white' }}>User Profile</h2>
        <form onSubmit={handleUpdate} className="form">
          <label htmlFor="username" style={{ color: 'white' }}>Username:</label>
          <input type="text" id="username" name="username" value={username} onChange={(e) => setUsername(e.target.value)} />

          <label htmlFor="name" style={{ color: 'white' }}>Name:</label>
          <input type="text" id="name" name="name" value={name} onChange={(e) => setName(e.target.value)} />

          <label htmlFor="email" style={{ color: 'white' }}>Email:</label>
          <input type="email" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />

          <label htmlFor="address" style={{ color: 'white' }}>Address:</label>
          <input type="text" id="address" name="address" value={address} onChange={(e) => setAddress(e.target.value)} />

          <label htmlFor="phoneNumber" style={{ color: 'white' }}>Phone Number:</label>
          <input type="text" id="phoneNumber" name="phoneNumber" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />

          <button type="submit">Update</button>
        </form>
      </header>
    </div>
  );
}

export default UserProfile;
