import { useEffect, useState } from "react";
import { getAllBorrowDetails } from "../service/borrowService";
import { toast } from "react-toastify";
import BorrowList from "../components/borrow";

function ViewRecords() {
    //to set title of the page
    document.title = "VIEW BORROW RECORDS";


    useEffect(() => {
      getDetails();
    }, [])

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

  const emptyArr = []

  const [borrowDetails, setBorrowDetails] = useState('')

  const [visibleDate, setVisibleDate] = useState(5); // Tracks how many books to show

  const handleShowMore = () => {
    setVisibleDate((prevVisibleData) => prevVisibleData + 5); // Show the next 4 books
  };


    const getDetails = async () =>{

      try {
        const all = await getAllBorrowDetails();
        console.log(JSON.stringify(all));
        setBorrowDetails(all)
      }catch(error){
        handleError(error);
      }

    }


  return (
    <div>
      <div className="header ">
      <h2 className='row justify-content-center pt-5 mb-0'>
                        View Borrow Records </h2>
      </div>
      <div className="container mt-4 p-4 bg-white shadow">
        {/* <div className="row mb-4">
          <div className="col">
            <input type="date" className="form-control" id="issueDate" name="borrowDate" defaultValue="2021-04-26" />
          </div>
          <div className="col">
            <input type="date" className="form-control" id="dueDate" name="dueDate" defaultValue="2021-04-29" />
          </div>
          <div className="col-auto">
            <button type="button" className="btn btn-danger">Search</button>
          </div>
        </div> */}
      {Object.keys(borrowDetails).length !== emptyArr.length ?
          <table className="table table-bordered">
              <thead className="thead-light">
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
              {borrowDetails.slice(0, visibleDate).map((b) =>{
                return (
                  <BorrowList
                  id = {b.id}
                  bookName = {b.bookName}
                  userName = {b.userName}
                  borrowDate = {b.borrowDate}
                  dueDate = {b.dueDate}
                  status = {b.status}
                  />
                )
              })}
              </tbody>
            </table> :
            <p>No Records Found!</p>
    
      } 
       {visibleDate < borrowDetails.length && (
        <button className="btn btn-primary mt-3" onClick={handleShowMore}>Show More</button>
      )}
      </div>
    </div>
  );
};

export default ViewRecords;

