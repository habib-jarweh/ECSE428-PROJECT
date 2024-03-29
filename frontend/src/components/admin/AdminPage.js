import React, { useState, useEffect } from 'react';

function AdminPage() {
  const [customers, setCustomers] = useState([]);
  const [meals, setMeals] = useState([]);
  const [currentView, setCurrentView] = useState(null);
  const [mealName, setMealName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [ingredients, setIngredients] = useState('');
  const [stockQuantity, setStockQuantity] = useState('');
  const [imageLink, setImageLink] = useState('');
  const [dietaryRestrictions, setDietaryRestrictions] = useState('');

  useEffect(() => {
    if (currentView === 'customers') {
      fetch('http://localhost:8080/customers/view_all')
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to fetch customers');
          }
          return response.json();
        })
        .then(data => setCustomers(data))
        .catch(error => console.error('Error fetching customers:', error));
    } else if (currentView === 'meals') {
      fetch('http://localhost:8080/meals/view_all')
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to fetch meals');
          }
          return response.json();
        })
        .then(data => setMeals(data))
        .catch(error => console.error('Error fetching meals:', error));
    }
  }, [currentView]);

  const deleteCustomer = async (email) => {
    try {
      const response = await fetch('http://localhost:8080/customers/delete', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email }),
      });

      if (!response.ok) {
        throw new Error('Failed to delete customer');
      }

      // Update the customer list after deletion
      setCustomers(customers.filter(customer => customer.email !== email));
    } catch (error) {
      console.error('Error deleting customer:', error);
    }
  };
  const createMeal = async (event) => {
    event.preventDefault(); // Prevent default form submission behavior
    try {
      const response = await fetch('http://localhost:8080/meals/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Admin-Token': 'RHPSOoCuK6QJBalhq4Cbv9yjhViKwXbXpioheADyw246gURS8GLcHsbPWb3cJBOs',
        },
        body: JSON.stringify({
          mealName,
          description,
          price: price ? parseFloat(price) : undefined,
          ingredients: ingredients ? ingredients.split(',').map(ingredient => ingredient.trim()) : [],
          // Add other fields similarly
        }),
      });
  
      if (!response.ok) {
        throw new Error('Failed to create meal');
      }
  
      const data = await response.json(); 
      console.log('Meal created successfully:', data);
      setMealName('');
      setDescription('');
      setPrice('');
      setIngredients('');
      setMealName('');
      setDescription('');
      setPrice('');
      setIngredients('');
      setStockQuantity('');
      setImageLink('');
      setDietaryRestrictions('');
    } catch (error) {
      console.error('Error creating meal:', error);
    }
  };
  
  return (
    <div style={styles.adminPageLayout}>
      <h1>
        Welcome, Admin!
      </h1>
      <div style={styles.navigation}>
        <button style={styles.button} onClick={() => setCurrentView('customers')}>Show Customers</button>
        <button style={styles.button} onClick={() => setCurrentView('meals')}>Show Meals</button>
        <button style={styles.button} onClick={() => setCurrentView('createMeal')}>Create Meal</button>
      </div>
      {currentView === 'customers' && (
        <div style={styles.content}>
          <h2>Customer List</h2>
          <table>
            <thead>
              <tr>
                <th>Email</th>
                <th>Name</th>
                <th>Address</th>
                <th>Billing Address</th>
                <th>Phone Number</th>
                <th>Dietary Restrictions</th>
                <th>Weight Goal</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
            {customers.filter(customer => !customer.email.includes('mcgill.ca')).map((customer) => (
              <tr key={customer.email}>
                <td>{customer.email}</td>
                <td>{customer.name || 'N/A'}</td>
                <td>{customer.address || 'N/A'}</td>
                <td>{customer.billingAddress || 'N/A'}</td>
                <td>{customer.phoneNumber || 'N/A'}</td>
                <td>{customer.dietaryRestrictions?.join(", ") || 'None'}</td>
                <td>{customer.weightGoal} kg</td>
                <td>
                  <button onClick={() => deleteCustomer(customer.email)}>Delete</button>
                </td>
              </tr>
            ))}
            </tbody>
          </table>
        </div>
      )}
      {currentView === 'meals' && (
        <div style={styles.content}>
          <h2>Meal List</h2>
          <table>
            <thead>
              <tr>
                <th>Meal Name</th>
                <th>Description</th>
                <th>Rating</th>
                <th>Price</th>
                <th>Ingredients</th>
                <th>Dietary Restrictions</th>
                <th>Image</th>
                <th>Stock Quantity</th>
              </tr>
            </thead>
            <tbody>
              {meals.map((meal, index) => (
                <tr key={index}>
                  <td>{meal.mealName}</td>
                  <td>{meal.description || 'N/A'}</td>
                  <td>{meal.rating || 'N/A'}</td>
                  <td>${meal.price.toFixed(2)}</td>
                  <td>{meal.ingredients.join(", ")}</td>
                  <td>{meal.dietaryRestrictions || 'None'}</td>
                  <td>{meal.imageLink ? <img src={meal.imageLink} alt="Meal" style={{width: '50px', height: '50px'}} /> : 'N/A'}</td>
                  <td>{meal.stockQuantity}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
      {currentView === 'createMeal' && (
        <div style={styles.content}>
          <h2>Create Meal</h2>
          <form onSubmit={createMeal}>
            <input
              type="text"
              placeholder="Meal Name"
              value={mealName}
              onChange={e => setMealName(e.target.value)}
              required
            />
            <input
              type="text"
              placeholder="Description"
              value={description}
              onChange={e => setDescription(e.target.value)}
            />
            <input
              type="text"
              placeholder="Price"
              value={price}
              onChange={e => setPrice(e.target.value)}
            />
            <input
              type="text"
              placeholder="Ingredients (comma-separated)"
              value={ingredients}
              onChange={e => setIngredients(e.target.value)}
            />
            <input
              type="number"
              placeholder="Stock Quantity"
              value={stockQuantity}
              onChange={e => setStockQuantity(e.target.value)}
            />
            <input
              type="text"
              placeholder="Image Link"
              value={imageLink}
              onChange={e => setImageLink(e.target.value)}
            />
            <input
              type="text"
              placeholder="Dietary Restrictions (comma-separated)"
              value={dietaryRestrictions}
              onChange={e => setDietaryRestrictions(e.target.value)}
            />
            <button type="submit">Create Meal</button>
          </form>
        </div>
      )}
    </div>
  );
}

const styles = {
  welcomeMessage: {
    textAlign: 'center',
    fontSize: '24px',
    color: '#333',
    margin: '20px 0',
  },
  adminPageLayout: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    marginTop: '20px',
  },
  navigation: {
    marginBottom: '20px',
  },
  content: {
    width: '80%',
    textAlign: 'center',
  },
  button: {
    cursor: 'pointer',
    backgroundColor: '#007bff',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    padding: '10px 20px',
    margin: '0 10px',
    fontWeight: 'bold',
  }
};

export default AdminPage;
