import React, { useState, useEffect } from 'react';

function AdminPage() {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/customers/view_all')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch customers');
        }
        return response.json();
      })
      .then(data => setCustomers(data))
      .catch(error => console.error('Error fetching customers:', error));
  }, []);

  const deleteCustomer = async (email) => {
    try {
      const response = await fetch('http://localhost:8080/customers/delete', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
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

  return (
    <div>
      <h1>Welcome Admin !</h1>
      <br></br>
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
          {customers.filter(customer => !customer.email.includes('mcgill')).map(customer => (
            <tr key={customer.email}>
              <td>{customer.email}</td>
              <td>{customer.name || 'N/A'}</td>
              <td>{customer.address || 'N/A'}</td>
              <td>{customer.billingAddress || 'N/A'}</td>
              <td>{customer.phoneNumber || 'N/A'}</td>
              <td>{customer.dietaryRestrictions?.length > 0 ? customer.dietaryRestrictions?.join(", ") : 'None'}</td>
              <td>{customer.weightGoal} kg</td>
              <td>
                <button onClick={() => deleteCustomer(customer.email)}>Delete</button>
              </td>
            </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminPage;
