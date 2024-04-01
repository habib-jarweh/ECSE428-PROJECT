import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './CustomerPage.css';


function CustomerPage() {
  const [meals] = useState([]);
  const [userInfo, setUserInfo] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const navigate = useNavigate();
  const handleViewMeals = () => {
    navigate('/meals');
  };

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

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserInfo((prev) => ({ ...prev, [name]: value }));
  };

  const handleSelectChange = (e) => {
    const { name, value } = e.target;
    // Assuming dietaryRestrictions can be an array for future flexibility
    setUserInfo((prev) => ({ ...prev, [name]: [value] }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Logic to update user info
    setEditMode(false);
  };


  return (
    <div className="customer-container"> {/* Make sure to use the class for styling */}
      <h1>Customer Dashboard</h1>
      {userInfo && (
        editMode ? (
          <form onSubmit={handleSubmit}>
            <div>
              <label>Email:</label>
              <input type="email" value={userInfo.email} readOnly />
            </div>
            <div>
              <label>Name:</label>
              <input
                type="text"
                name="name"
                value={userInfo.name || ''}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <label>Address:</label>
              <input
                type="text"
                name="address"
                value={userInfo.address || ''}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <label>Billing Address:</label>
              <input
                type="text"
                name="billingAddress"
                value={userInfo.billingAddress || ''}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <label>Phone Number:</label>
              <input
                type="text"
                name="phoneNumber"
                value={userInfo.phoneNumber || ''}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <label>Dietary Restrictions:</label>
          <select
            multiple
            name="dietaryRestrictions"
            value={userInfo.dietaryRestrictions || []}
            onChange={handleSelectChange}
          >
            <option value="Vegetarian">Vegetarian</option>
            <option value="Vegan">Vegan</option>
            <option value="Gluten-Free">Gluten-Free</option>
            <option value="Halal">Halal</option>
            <option value="Peanut-Free">Peanut-Free</option>
            {/* Add more options as necessary */}
          </select>

            </div>
            <div>
              <label>Weight Goal:</label>
              <input
                type="text"
                name="weightGoal"
                value={userInfo.weightGoal || ''}
                onChange={handleInputChange}
              />
            </div>
            <button type="submit">Save Changes</button>
            <button type="button" onClick={() => setEditMode(false)}>Cancel</button>
          </form>
        ) : (
          <div className="user-info">
            <h2>User Info</h2>
            <p>Email: {userInfo.email || "Not provided"}</p>
            <p>Name: {userInfo.name || "Not provided"}</p>
            <p>Address: {userInfo.address || "Not provided"}</p>
            <p>Billing Address: {userInfo.billingAddress || "Not provided"}</p>
            <p>Phone Number: {userInfo.phoneNumber || "Not provided"}</p>
            <p>Dietary Restrictions: {userInfo.dietaryRestrictions?.join(", ") || "None"}</p>
            <p>Weight Goal: {userInfo.weightGoal ? `${userInfo.weightGoal} kg` : "Not provided"}</p>
            <button onClick={() => setEditMode(true)}>Edit Info</button>
          </div>
        )
      )}
      <button onClick={handleViewMeals}>View Meals</button>
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
