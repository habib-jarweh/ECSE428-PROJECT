import React, { useState } from 'react';
import logo from '../logo.png'; // Ensure the logo path is correct
import './SignUp.css';

const SignUp = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    name: '',
    email: '',
    address: '',
    phoneNumber: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Perform the POST request to your backend
    fetch('http://localhost:8080/customers/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
    .then(response => {
      if (response.ok) {
        console.log('Registration successful');
        // Handle redirection or further actions here
      } else {
        console.error('Registration failed');
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
  };

  return (
    <div className="container">
      <header>
        <img src={logo} className="logo" alt="logo" />
        <h2>Sign Up</h2>
      </header>

      <form className="form" onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input type="text" id="username" name="username" required value={formData.username} onChange={handleChange} />

        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" required value={formData.password} onChange={handleChange} />

        <label htmlFor="name">Name:</label>
        <input type="text" id="name" name="name" required value={formData.name} onChange={handleChange} />

        <label htmlFor="email">Email:</label>
        <input type="email" id="email" name="email" required value={formData.email} onChange={handleChange} />

        <label htmlFor="address">Address:</label>
        <input type="text" id="address" name="address" value={formData.address} onChange={handleChange} />

        <label htmlFor="phoneNumber">Phone Number:</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} />

        <button type="submit">Sign Up</button>
      </form>
      <a href="/signin" className="SignUp-link">Already have an account? Sign In</a>
    </div>
  );
};

export default SignUp;
