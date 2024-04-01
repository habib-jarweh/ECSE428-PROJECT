// MealsPage.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import './MealsPage.css'; // Ensure you have the corresponding CSS file for styling

function MealsPage() {
  const [meals, setMeals] = useState([]);
  const navigate = useNavigate(); // Initialize useNavigate


  useEffect(() => {
    const fetchMeals = async () => {
      try {
        const response = await fetch('http://localhost:8080/meals/view_all');
        if (!response.ok) {
          throw new Error('Could not fetch meals');
        }
        const data = await response.json();
        setMeals(data);
      } catch (error) {
        console.error('Error fetching meals:', error);
      }
    };

    fetchMeals();
  }, []);

    // Function to navigate back to the CustomerPage
    const handleBackToDashboard = () => {
        navigate('/customer'); // Adjust the path as necessary based on your routing setup
      };

  return (
    <div className="meals-container">
      <h1>Meals</h1>
      <button onClick={handleBackToDashboard}>Back to Dashboard</button> {/* Button to go back */}
      {meals.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Meal Name</th>
              <th>Description</th>
              <th>Rating</th>
              <th>Price</th>
              <th>Ingredients</th>
              <th>Dietary Restrictions</th>
              <th>Stock Quantity</th>
              <th>Image</th>
            </tr>
          </thead>
          <tbody>
            {meals.map((meal) => (
              <tr key={meal.id}>
                <td>{meal.mealName}</td>
                <td>{meal.description || "Not provided"}</td>
                <td>{meal.rating || "Not rated"}</td>
                <td>${meal.price.toFixed(2)}</td>
                <td>{meal.ingredients?.join(", ") || "Not provided"}</td>
                <td>{meal.dietaryRestrictions?.join(", ") || "None"}</td>
                <td>{meal.stockQuantity || "Not provided"}</td>
                <td>
                  {meal.imageLink && (
                    <img src={meal.imageLink} alt={meal.mealName} style={{width: '50px', height: '50px'}} />
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No meals available.</p>
      )}
    </div>
  );
}

export default MealsPage;
