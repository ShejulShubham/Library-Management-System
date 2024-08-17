import { useState } from 'react';
import { getBorrowDetails, returnBook } from '../service/borrowService';
import { toast } from 'react-toastify';
import { useEffect } from 'react';

const ReturnBook = () => {
  //to set title of the page
  document.title = "RETURN BOOK";

  const [borrowDetails, setBorrowDetails] = useState([]);
  // const [book, setBookId] = useState(0);
  const [userId, setUserId] = useState('');
  

  useEffect(() => {
    setUserId(localStorage['userId']);
  }, [])

  const handleFindDetails = async (e) => {
    e.preventDefault();

          await getBorrowDetails(userId)
        .then(response => {

          setBorrowDetails(response)
          // setBookId(borrowDetails.bookId)
          // console.log(JSON.stringify(borrowDetails))
          // setUserId(borrowDetails.userId)
        })
        .catch(error => {
              if (error.response) {
                // The request was made and the server responded with a status code
                // that falls out of the range of 2xx
                console.log("Error Data:", error.response.data);
                toast.error(error.response.data.message);
                console.log("Error Status:", error.response.status);
                console.log("Error Headers:", error.response.headers);
            } else if (error.request) {
                // The request was made but no response was received
                console.log("Error Request:", error.request);
            } else {
                // Something happened in setting up the request that triggered an Error
                console.log("Error Message:", error.message);
            }
    })


          // setBookId(borrowDetails.bookId)
          console.log(JSON.stringify(borrowDetails))

  };

  const handleReturnBook = async (e) => {
    e.preventDefault();
    console.log(JSON.stringify(borrowDetails))

          const body = {
            userId,
            bookId: borrowDetails.bookId,
          }


          await returnBook(body)
          .then(response => {

                toast.success(response['message'])
                // setBookId('')
                // setUserId('')
                setBorrowDetails([]);
          })
          .catch(error => {
                if (error.response) {
                  // The request was made and the server responded with a status code
                  // that falls out of the range of 2xx
                  console.log("Error Data:", error.response.data);
                  toast.error(error.response.data.message);
                  console.log("Error Status:", error.response.status);
                  console.log("Error Headers:", error.response.headers);
              } else if (error.request) {
                  // The request was made but no response was received
                  console.log("Error Request:", error.request);
              } else {
                  // Something happened in setting up the request that triggered an Error
                  console.log("Error Message:", error.message);
              }
          })

    

  };

  return (
          <div className="container d-flex flex-column justify-content-between h-100">
            <div className="return-book col-md-6 d-flex flex-column justify-content-between align-items-center px-5 py-3 mx-auto">
              {/* <div className="close-button">&times;</div> */}
              <h2>Return Book</h2>
              {/* <input type="text" className="form-control mb-3"
              value={bookId}
              onChange={(e) => setBookId(e.target.value)}
              required
              placeholder="Enter Book Id..." /> */}
              {/* <input type="text" className="form-control mb-3" 
              value={userId}
              onChange={(e) => setUserId(e.target.value)}
              required
              placeholder="Enter User Id..." /> */}
            </div>

            <div className="issued-book-details col-md-6 bg-danger text-white d-flex flex-column justify-content-center align-items-start px-5 py-3 mx-auto mt-3">
              {/* <img src="book-icon.png" alt="Book Icon" width="50" /> */}
              <h2>Borrowed Book Details</h2>
              {borrowDetails.id > 0 ? (
                  <div className='show-details'>
                    <p>Borrow Id: {borrowDetails.id}</p>
                    <p>Book Id: {borrowDetails.bookId}</p>
                    <p>Book Name: {borrowDetails.bookName}</p>
                    <p>User Name: {borrowDetails.userName}</p>
                    <p>Borrow Date: {borrowDetails.borrowDate}</p>
                    <p>Due Date: {borrowDetails.dueDate}</p>
                  </div>
              ) : (
                <p> Please click on Find Details first to get record</p>
              )}
              <div className="d-flex justify-content-between w-100">
                <button className="btn btn-primary me-3"
                onClick={handleFindDetails}>Find Details</button>
                <button className="btn btn-danger"
                onClick={handleReturnBook}>Return Book</button>
              </div>
            </div>
            
          </div>

  );
}

export default ReturnBook;
