import React, { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import { DashboadDetails } from '../service/borrowService';
import userDash from '../components/userDash';

function Dashboard() {
  //to set title of the page
  document.title = "ADMIN DASHBOARD";

  useEffect(() => {
      DashDetails();
  }, [])

  const emptyArr = []

  const [Dash, setDash] = useState({
    bookCount: 0,
    userCount: 0,
    borrowCount: 0,
    defaulterCount: 0,
    users: [],
    books: [],
  })

  const DashDetails = async () => {
      try {
        const all = await DashboadDetails();
        setDash(all)
        // console.log(JSON.stringify(all))
        // console.log(Dash)
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
    <div class="container">
      
      <div class="row text-center mb-4">
      <div class="col-md-3 counter bg-white p-4 shadow-sm rounded">
        <h3>No. of Books</h3>
        <p>{Dash.bookCount}</p>
      </div>
      <div class="col-md-3 counter bg-white p-4 shadow-sm rounded">
        <h3>No. of Users</h3>
        <p>{Dash.userCount}</p>
      </div>
      <div class="col-md-3 counter bg-white p-4 shadow-sm rounded">
        <h3>Borrowed Books</h3>
        <p>{Dash.borrowCount}</p>
      </div>
      <div class="col-md-3 counter bg-white p-4 shadow-sm rounded">
        <h3>Defaulter List</h3>
        <p>{Dash.defaulterCount}</p>
      </div>
    </div>
  
    <div class="row">
      <div class="col-md-6 mb-4">
        <h3>User Details ("First Four Only")</h3>
            <table class="table table-striped table-bordered table-hover">
            <thead>
              <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              {Dash.users.slice(0, 4).map((d) => {
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
      <div class="col-md-6 mb-4">
        <h3>Book Details ("First Four Only")</h3>
          <table class="table table-striped table-bordered table-hover">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Author</th>
                <th>Quantity</th>
              </tr>
            </thead>
            <tbody>
              {Dash.books.slice(0, 4).map((b) => {
                return (
                  <tr>
                    <th>{b.bookId}</th>
                    <th>{b.bookTitle}</th>
                    <th>{b.authors.map((author) => author.authorName).join(", ")}</th>
                    <th>{b.quantity}</th>
                </tr>
                )
              })}
            </tbody>
          </table>
      </div>
    </div>

  
    </div>
  );
}

export default Dashboard;