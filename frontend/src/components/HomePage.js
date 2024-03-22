import React from 'react';
import { Link } from 'react-router-dom';

function HomePage() {
  return (
    <div style={styles.container}>
      <header style={styles.header}>
        <h1>Welcome to SmartEats!</h1>
        <p>Making healthy eating simple and accessible for everyone.</p>
      </header>
      <section style={styles.section}>
        <h2>Why SmartEats?</h2>
        <p>At SmartEats, we believe that everyone deserves access to healthy, nutritious meals that support their dietary needs and goals. Whether you're looking for plant-based options, counting macros, or just trying to eat a little healthier, we've got you covered.</p>
      </section>
      <section style={styles.section}>
        <h2>Featured Meals</h2>
        <p>Discover meals curated by our nutritionists, designed to fuel your body and satisfy your taste buds.</p>
        {/* Ideally, you'd fetch and display some featured meals here */}
      </section>
      <footer style={styles.footer}>
        <Link to="/signin" style={styles.link}>Sign In</Link> | 
        <Link to="/signup" style={styles.link}> Sign Up</Link>
        <p>Follow us on social media to stay updated on the latest meals and offers!</p>
        {/* Social media links */}
      </footer>
    </div>
  );
}

const styles = {
  container: {
    textAlign: 'center',
    padding: '0 20px',
  },
  header: {
    backgroundColor: '#4CAF50',
    color: 'white',
    padding: '20px 0',
    marginBottom: '30px',
  },
  section: {
    margin: '20px 0',
  },
  footer: {
    marginTop: '30px',
    paddingTop: '20px',
    borderTop: '1px solid #ccc',
  },
  link: {
    textDecoration: 'none',
    color: '#4CAF50',
    margin: '0 10px',
  }
};

export default HomePage;
