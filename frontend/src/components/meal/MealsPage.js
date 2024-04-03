import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './MealsPage.css';

function MealsPage() {
  const [meals, setMeals] = useState([]);
  const [successful, setSuccessful] = useState(false); // using useState for the 'successful' variable
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMeals = async () => {
      try {
        const response = await fetch('http://localhost:8080/meals/view_all');
        if (!response.ok) {
          throw new Error('Could not fetch meals');
        }
        const data = await response.json();
        const updatedMeals = data.map(meal => ({ ...meal, orderQuantity: 0 })); // Initialize orderQuantity for each meal
        setMeals(updatedMeals);
      } catch (error) {
        console.error('Error fetching meals:', error);
      }
    };

    fetchMeals();
  }, []);

  const handleBackToDashboard = () => {
    navigate('/customer');
  };

  const handleOrder = async () => {

    const email = localStorage.getItem('userEmail'); // Retrieve the user's email from localStorage
    if (email) {

      const order = { customerEmail: null, mealNames: [] }; // Initialized with an empty array for meal names
      order.customerEmail = email;
      var total = 0;

      meals.forEach((meal) => {
        total += meal.price * meal.orderQuantity;

        for (let i = 0; i < meal.orderQuantity; i++) {
          order.mealNames.push(meal.mealName);
        }
      });

      order.total = total;
      try {
        const response = await fetch(`http://localhost:8080/orders/create`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(order)
        });
        if (!response.ok) {
          throw new Error('Could not make order');
        }

        setSuccessful(true);
      } catch (error) {
        console.error('Error ordering:', error);
      }
    }
  };
  const handleIncrease = (meal) => {
    const updatedMeals = meals.map(m => {
      if (m.mealName === meal.mealName) {
        return { ...m, orderQuantity: m.orderQuantity + 1 };
      }
      return m;
    });
    setMeals(updatedMeals);
  };

  const handleDecrease = (meal) => {
    const updatedMeals = meals.map(m => {
      if (m.mealName === meal.mealName && m.orderQuantity > 0) {
        return { ...m, orderQuantity: m.orderQuantity - 1 };
      }
      return m;
    });
    setMeals(updatedMeals);
  };

  return (
    <div className="meals-container">
      <h1>Meals</h1>
      <button onClick={handleBackToDashboard}>Back to Dashboard</button>
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
              <th>Order Quantity</th>
            </tr>
          </thead>
          <tbody>
            {meals.map((meal) => (
              <tr key={meal.mealName}>
                <td>{meal.mealName}</td>
                <td>{meal.description || "Not provided"}</td>
                <td>{meal.rating || "Not rated"}</td>
                <td>${meal.price.toFixed(2)}</td>
                <td>{meal.ingredients?.join(", ") || "Not provided"}</td>
                <td>{meal.dietaryRestrictions?.join(", ") || "None"}</td>
                <td>{meal.stockQuantity || "Not provided"}</td>
                <td>
                  <button onClick={() => handleDecrease(meal)}>-</button>
                  <span>{meal.orderQuantity}</span>
                  <button onClick={() => handleIncrease(meal)}>+</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No meals available.</p>
      )}
      <button onClick={handleOrder}>Order</button>
      <div>
        {successful ? <p>Your order was successful!</p> : <p></p>}
      </div>
    </div>
  );
}

export default MealsPage;