import React, { useState, useEffect } from 'react';

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
    } catch (error) {
      console.error('Error fetching meals:', error);
    }
  };

  return (
    <div>
      <h1>Customer Dashboard</h1>
      {userInfo && (
        <div>
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
        <div>
          <h2>Meals</h2>
          {meals.map((meal) => (
            <div key={meal.id}>
              <h3>{meal.mealName}</h3>
              <p>Description: {meal.description || "Not provided"}</p>
              <p>Rating: {meal.rating || "Not rated"}</p>
              <p>Price: ${meal.price}</p>
              <p>Ingredients: {meal.ingredients.join(", ") || "Not provided"}</p>
              <p>Dietary Restrictions: {meal.dietaryRestrictions?.join(", ") || "None"}</p>
              <p>Stock Quantity: {meal.stockQuantity}</p>
              {meal.imageLink && <img src={meal.imageLink} alt={meal.mealName} style={{width: '100px', height: '100px'}} />}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default CustomerPage;
