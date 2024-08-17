import React, { useState, useEffect } from 'react';

const EditUser = ({ userId }) => {
  const [user, setUser] = useState({
    first_name: '',
    last_name: '',
    email: '',
    role: '',
    password: '',
    // user_image_path: '',
  });

  useEffect(() => {
    // Fetch user data based on userId
    fetch(`/api/users/${userId}`)
      .then((response) => response.json())
      .then((data) => setUser(data));
  }, [userId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({
      ...user,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Update user data
    fetch(`/api/users/${userId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(user),
    })
      .then((response) => response.json())
      .then((data) => {
        alert('User updated successfully');
      });
  };

  return (
    <div className="container mt-5">
      <div className="card">
        <div className="card-body">
          <h4 className="card-title text-center mb-4">Edit User</h4>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>First Name</label>
              <input
                type="text"
                className="form-control"
                name="first_name"
                value={user.first_name}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>Last Name</label>
              <input
                type="text"
                className="form-control"
                name="last_name"
                value={user.last_name}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                className="form-control"
                name="email"
                value={user.email}
                onChange={handleChange}
              />
            </div>
           
            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                className="form-control"
                name="password"
                value={user.password}
                onChange={handleChange}
              />
            </div>
            {/* <div className="form-group">
              <label>User Image Path</label>
              <input
                type="text"
                className="form-control"
                name="user_image_path"
                value={user.user_image_path}
                onChange={handleChange}
              />
            </div> */}
            <button type="submit" className="btn btn-primary btn-block">
              Save Changes
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default EditUser;
