

import React from 'react';
import { Navigate } from 'react-router-dom';

const AdminRoute = ({ children }) => {
    // Assuming you have stored the role in localStorage
    const userRole = localStorage.getItem('role');

    // Check if the user is an admin
    if (userRole === 'ROLE_ADMIN') {
        return children;
    } else {
        // If the user is not an admin, redirect to the login page or home page
        return <Navigate to="/login" />;
    }
};

export default AdminRoute;
