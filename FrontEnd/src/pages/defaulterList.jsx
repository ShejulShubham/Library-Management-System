import React, { useState, useEffect } from 'react';
import { toast } from 'react-toastify';
import { getDefaultersList } from '../service/borrowService';

const DefaulterList = () => {
  //to set title of the page
  document.title = "DEFAULTER LIST";

  const [defaulters, setDefaulters] = useState([]);

  useEffect(() => {
    defaulterDetails();
  }, []);

  function handleError(error) {
    if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.error('Server responded with an error:', error.response.data);
        toast.error(`Please try again later. ${error.response.data.message}`);
    } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received:', error.request);
        toast.error(error.request);
    } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up the request:', error.message);
        toast.error(error.message);
        // alert('An unexpected error occurred. Please try again.');
    }
  }


  const defaulterDetails = async () =>{
    // e.preventDefault();

    try{
      const data = await getDefaultersList();

      // console.log(JSON.stringify(data))

      setDefaulters(data);

    }catch(error){
      handleError(error);
    }
    
  }

  return (
    <div className="container my-5">
      <h2 className="text-center mb-4">Defaulter List</h2>
      <div className="d-flex justify-content-center">
        {/* <img src="" alt="Defaulter List" className="img-fluid mb-4" style={{ maxWidth: '100%' }} /> */}
      </div>
      <div className="table-responsive">
        <table className="table table-bordered text-center">
          <thead className="thead-dark">
            <tr>
              <th>Borrow Id</th>
              <th>Book Name</th>
              <th>User Name</th>
              <th>Borrow Date</th>
              <th>Due Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {defaulters.length > 0 ? (
              defaulters.map(defaulter => (
                <tr key={defaulter.id} className="table-primary">
                  <td>{defaulter.id}</td>
                  <td>{defaulter.bookName}</td>
                  <td>{defaulter.userName}</td>
                  <td>{defaulter.borrowDate}</td>
                  <td>{defaulter.dueDate}</td>
                  <td className={defaulter.status.toLowerCase() === 'borrowed' ? 'text-danger' : ''}>
                    {defaulter.status}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6">No defaulters found</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default DefaulterList;
