import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SignUp() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    try {
      const response = await fetch('http://localhost:8080/customers/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
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
    <div>
      <h1>Sign Up</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default SignUp;
