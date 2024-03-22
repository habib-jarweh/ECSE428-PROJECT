import React from 'react';
import { Link } from 'react-router-dom';

function AdminPage() {
  return (
    <div>
      <h1>Admin Dashboard</h1>
      <p>Welcome, Admin!</p>
      {/* Placeholder for admin functionalities */}
      <div>
        <Link to="/users">View Customers</Link> {/* Link to the customer list component/page */}
        {/* Add more links or buttons for other admin functions */}
      </div>
    </div>
  );
}

export default AdminPage;
