// UserDashboard.js
import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

function UserDashboard() {
  const [dietaryRestriction, setDietaryRestriction] = useState('');
  const [currentWeight, setCurrentWeight] = useState('');
  const [weightGoal, setWeightGoal] = useState('');

  return (
    <div className="App">
      <header className="App-header">
        <nav style={{ width: '100%', display: 'flex', justifyContent: 'flex-start', alignItems: 'center' }}>
          <img src={logo} className="App-logo" alt="logo" style={{ marginRight: '20px' }} onClick={() => {/* Logic to navigate back to dashboard */}} />
          <button onClick={() => {/* Navigate to User Profile */}}>User Profile</button>
          <button onClick={() => {/* Navigate to Meal Information */}}>Meal Information</button>
          {/* Add more navigation buttons as needed */}
        </nav>
        <h2 style={{ color: 'white' }}>User Dashboard</h2>
        <div className="form">
          <label htmlFor="dietaryRestriction" style={{ color: 'white' }}>Dietary Restriction:</label>
          <select id="dietaryRestriction" value={dietaryRestriction} onChange={(e) => setDietaryRestriction(e.target.value)}>
            <option value="">Select</option>
            <option value="vegan">Vegan</option>
            <option value="vegetarian">Vegetarian</option>
            <option value="allergies">Allergies</option>
            <option value="intolerances">Intolerances</option>
          </select>

          <label htmlFor="currentWeight" style={{ color: 'white' }}>Current Weight (kg):</label>
          <input type="number" id="currentWeight" name="currentWeight" value={currentWeight} onChange={(e) => setCurrentWeight(e.target.value)} />

          <label htmlFor="weightGoal" style={{ color: 'white' }}>Weight Goal (kg):</label>
          <input type="number" id="weightGoal" name="weightGoal" value={weightGoal} onChange={(e) => setWeightGoal(e.target.value)} />
        </div>
      </header>
    </div>
  );
}

export default UserDashboard;
