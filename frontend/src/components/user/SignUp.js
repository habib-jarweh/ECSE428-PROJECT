import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SignUp.css'; // Adjust the path if necessary
import welcomeImage from './welcome.png'; // Adjust path as necessary
import logo from './logo.png'; // Adjust path as necessary
import { Link } from 'react-router-dom';


function SignUp() {
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [address, setAddress] = useState('');
  const [billingAddress, setBillingAddress] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/customers/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password, name, address, billingAddress, phoneNumber }),
      });

      if (!response.ok) {
        // Assuming the API responds with an error message in the body
        const errorResponse = await response.json();
        throw new Error(errorResponse.message || 'Registration failed');
      }

      // Assuming registration automatically logs the user in and you wish to store the email
      localStorage.setItem('userEmail', email);

      // Redirect to the customer page after successful registration
      navigate('/customer'); // Update this line to match the route of your customer page
    } catch (error) {
      console.error('Registration error:', error.message);
      // Here, you could set an error state and display the message to the user
    }
  };


  return (
    <div className="container">
      <img src={welcomeImage} alt="Welcome" className="welcome-image" />
      <div className="top-left-logo">
        <img src={logo} alt="Logo" className="small-logo" />
      </div>
      <h2>Sign Up</h2>
      <form onSubmit={handleSubmit} className="form-container">
        <div className="inputGroup">
          <label>Email</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="inputGroup">
          <label>Name</label>
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div className="inputGroup">
          <label>Password</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <div className="inputGroup">
          <label>Address</label>
          <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} />
        </div>
        <div className="inputGroup">
          <label>Billing Address</label>
          <input type="text" value={billingAddress} onChange={(e) => setBillingAddress(e.target.value)} />
        </div>
        <div className="inputGroup">
          <label>Phone Number</label>
          <input type="text" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
        </div>
        <button type="submit">Sign Up</button>
        <p className="SignIn-link">Already have an account? <Link to="/signin">Sign In!</Link></p>
      </form>
    </div>
  );
}

export default SignUp;
