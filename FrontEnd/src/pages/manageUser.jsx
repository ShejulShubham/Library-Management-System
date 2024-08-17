import React, { useEffect, useState } from 'react';
import { getUsers } from '../service/userService';
import { toast } from 'react-toastify';

const ManageUsers = () => {
    //to set title of the page
    document.title = "MANAGE USERS";
  // const [userId, setuserId] = useState('');
  // const [userName, setuserName] = useState('');
  // const [courseName, setCourseName] = useState('BSC');
  // const [branchName, setBranchName] = useState('CS');
  const [data, setData] = useState([])

  useEffect(() => {
    UserDetails();
}, [])

  const UserDetails = async () => {
    try {
      const all = await getUsers();
      setData(all)
      console.log(JSON.stringify(all))
      // console.log(data)
  } catch (error) {
      handleError(error);
  }
}

function handleError(error) {
  if (error.response) {
      // The request was made and the server responded with a status code
      // that falls out of the range of 2xx
      console.error('Server responded with an error:', error.response.data);
      toast.error('Please try again later.');
  } else if (error.request) {
      // The request was made but no response was received
      console.error('No response received:', error.request);
      toast.error('No response from the server.');
  } else {
      // Something happened in setting up the request that triggered an Error
      console.error('Error setting up the request:', error.message);
      // alert('An unexpected error occurred. Please try again.');
  }
}

  return (
    <div>
        <div className="d-flex">
          {/* <div className="sidebar bg-primary text-white p-4">
            <div className="mb-3">
              <label className="form-label">Enter User ID</label>
              <input type="text" className="form-control" placeholder="Enter User ID" />
            </div>
            <div className="mb-3">
              <label className="form-label">Enter User Name</label>
              <input type="text" className="form-control" placeholder="Enter User Name" />
            </div>
        <div className="d-grid gap-2">
          <button className="btn btn-danger">ADD</button>
          <button className="btn btn-danger">UPDATE</button>
          <button className="btn btn-danger">DELETE</button>
        </div>
      </div> */}
      <div className="content p-4 w-100">
        <div className="header d-flex justify-content-between align-items-center mb-4">
          <h1 >Manage Users</h1>
        </div>
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>User Id</th>
              <th>Name</th>
              <th>Email</th>
            </tr>
          </thead>
          <tbody>
          {data.map((d) => {
                return (
                  <tr>
                    <th>{d.id}</th>
                    <th>{d.firstName}</th>
                    <th>{d.email}</th>
                </tr>
                )
              })}
          </tbody>
        </table>
      </div>
    </div>
    </div>
  );
};

export default ManageUsers;
