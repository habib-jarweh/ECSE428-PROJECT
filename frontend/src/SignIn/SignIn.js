import React, { useState } from 'react';
import logo from '../logo.png'; // Ensure the logo path is correct

const SignIn = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();

    const signInData = { email, password };

    // Send POST request to sign-in endpoint
    fetch("http://localhost:8080/customers/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(signInData)
    })
    .then(response => {
      if (response.ok) {
        console.log("Sign-in successful");
        // Redirect or perform desired action
      } else {
        console.error("Sign-in failed");
      }
    })
    .catch(error => {
      console.error("Error:", error);
    });
  };

  return (
    <div className="container" style={styles.container}>
      <header>
        <img src={logo} className="logo" alt="logo" style={styles.logo} />
        <h2>Sign In</h2>
      </header>

      <form className="form" id="signInForm" style={styles.form} onSubmit={handleSubmit}>
        <label htmlFor="email">Email:</label>
        <input type="email" id="email" name="email" required style={styles.input}
               value={email} onChange={e => setEmail(e.target.value)} />
        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" required style={styles.input}
               value={password} onChange={e => setPassword(e.target.value)} />
        <button type="submit" style={styles.button}>Sign In</button>
      </form>
      <a href="/signup" className="SignIn-link" style={styles.signUpLink}>Don't have an account? Sign Up</a>
    </div>
  );
};

// Styles
const styles = {
  container: {
    textAlign: 'center',
  },
  logo: {
    height: '100px',
  },
  form: {
    marginTop: '20px',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  input: {
    display: 'block',
    marginBottom: '10px',
    color: 'black', // Change text color to black
  },
  button: {
    backgroundColor: '#61dafb',
    color: '#282c34',
    border: 'none',
    padding: '10px 20px',
    cursor: 'pointer',
  },
  signUpLink: {
    color: 'white',
    textDecoration: 'none',
    marginTop: '10px',
    display: 'block',
  },
};

export default SignIn;
