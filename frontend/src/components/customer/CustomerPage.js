import React, { useState, useEffect } from 'react';
import './CustomerPage.css';


function CustomerPage() {
  const [meals, setMeals] = useState([]);
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    const fetchUserInfo = async () => {
      const email = localStorage.getItem('userEmail'); // Retrieve the user's email from localStorage
      if (email) {
        try {
            const response = await fetch(`http://localhost:8080/customers/userInfo/${encodeURIComponent(email)}`, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' }
            });
          if (!response.ok) {
            throw new Error('Could not fetch user info');
          }
          const data = await response.json();
          setUserInfo(data);
        } catch (error) {
          console.error('Error fetching user info:', error);
        }
      }
      
    };

    fetchUserInfo();
  }, []);

  const fetchMeals = async () => {
    try {
      const response = await fetch('http://localhost:8080/meals/view_all');
      if (!response.ok) {
        throw new Error('Could not fetch meals');
      }
      const data = await response.json();
      setMeals(data);
      console.log(data);
    } catch (error) {
      console.error('Error fetching meals:', error);
    }
  };

  return (
    <div className="customer-container"> {/* Make sure to use the class for styling */}
      <h1>Customer Dashboard</h1>
      {userInfo && (
        <div className="user-info">
          <h2>User Info</h2>
          <p>Email: {userInfo.email || "Not provided"}</p>
          <p>Name: {userInfo.name || "Not provided"}</p>
          <p>Address: {userInfo.address || "Not provided"}</p>
          <p>Billing Address: {userInfo.billingAddress || "Not provided"}</p>
          <p>Phone Number: {userInfo.phoneNumber || "Not provided"}</p>
          <p>Dietary Restrictions: {userInfo.dietaryRestrictions?.join(", ") || "None"}</p>
          <p>Weight Goal: {userInfo.weightGoal ? `${userInfo.weightGoal} kg` : "Not provided"}</p>
        </div>
      )}
      <button onClick={fetchMeals}>View Meals</button>
      {meals.length > 0 && (
        <div className="meals">
          <h2>Meals</h2>
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
                  <td>${meal.price}</td>
                  <td>{meal.ingredients?.length > 0 ? meal.ingredients?.join(", ") : "Not provided"}</td>
                  <td>{meal.dietaryRestrictions?.join(", ") || "None"}</td>
                  <td>{meal.stockQuantity || "Not provided"}</td>
                  <td>
                    {meal.imageLink && (
                      <img src={meal.imageLink} alt={meal.mealName} className="meal-image" />
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}  

export default CustomerPage;
