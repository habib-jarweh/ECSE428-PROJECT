import React, { useState } from 'react';
import './SignIn.css'; // Adjust the path if necessary
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import logo from './logo.png';

function SignIn() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/customers/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }

      // Storing email and password in localStorage
      // Note: Storing passwords in localStorage is not recommended for production applications due to security concerns.
      localStorage.setItem('userEmail', email);
      localStorage.setItem('userPassword', password); // Highly discouraged in production

      // Redirect based on the email address
      if (email === 'habib.jarweh@mail.mcgill.ca') {
        navigate('/admin'); // Navigate to AdminPage
      } else {
        navigate('/customer'); // Navigate to CustomerPage
      }
    } catch (error) {
      console.error('Error during login:', error);
      // Here, you can update the UI to show an error message
    }
  };

  return (
    <div className="container">
      <img src={logo} alt="SmartEats Logo" className="logo" /> {/* Logo added here */}
      <h2>Sign In to SmartEats</h2>
      <form onSubmit={handleSubmit} className="form-container">
        <div className="inputGroup">
          <label>Email</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="inputGroup">
          <label>Password</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <button type="submit">Sign In</button>
        <p className="SignUpLink">Don't have an account? <Link to="/signup">Sign Up</Link></p>
      </form>
    </div>
  );  
}
export default SignIn;