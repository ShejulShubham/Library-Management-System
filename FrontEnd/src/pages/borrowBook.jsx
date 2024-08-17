import React, { useState, useEffect } from 'react';
import { toast } from 'react-toastify';
import { getBook } from '../service/bookService';
import { borrowBook } from '../service/borrowService';
import './BorrowBook.css'; // Import custom CSS file

function BorrowBook() {
  document.title = "BORROW BOOK";

  const [bookId, setBookId] = useState('');
  const [userId, setUserId] = useState(localStorage['userId']);
  const [borrowDate, setBorrowDate] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [status, setStatus] = useState('BORROWED');
  const [bookName, setBookName] = useState('');
  const [author, setAuthor] = useState('');
  const [quantity, setQuantity] = useState('');
  const [userName, setUserName] = useState('');

  useEffect(() => {
    // Set initial borrow date to today
    const currentDate = new Date().toJSON().slice(0, 10);
    setBorrowDate(currentDate);
  }, []);

  useEffect(() => {
    if (borrowDate) {
      calculateAndSetDueDate(borrowDate);
    }
  }, [borrowDate]);

  const handleError = (error) => {
    if (error.response) {
      console.log("Error Data:", error.response.data);
      toast.error(error.response.data.message);
    } else if (error.request) {
      console.log("Error Request:", error.request);
      toast.error("Server error please try again");
    } else {
      console.log("Error Message:", error.message);
    }
  };

  const calculateAndSetDueDate = (borrowDate) => {
    // Calculate due date (example: 14 days from the borrow date)
    const borrowDateObj = new Date(borrowDate);
    borrowDateObj.setDate(borrowDateObj.getDate() + 14); // Replace 14 with your `BORROW_PERIOD_DAYS`
    setDueDate(borrowDateObj.toJSON().slice(0, 10)); // Format date as yyyy-mm-dd
  };

  const handleBorrowDateChange = (e) => {
    const selectedBorrowDate = e.target.value;
    setBorrowDate(selectedBorrowDate);
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    
    const details = { bookId, userId, status, borrowDate, dueDate };

    try {
      const response = await borrowBook(details);
      toast.success(response['message']);
    } catch (error) {
      handleError(error);
    }
  };

  const getBookDetails = async (e) => {
    e.preventDefault();

    try {
      const response = await getBook(bookId);
      setBookName(response['bookTitle']);
      setQuantity(response['quantity']);
      setAuthor(response['authors'][0]['authorName']);
    } catch (error) {
      handleError(error);
    }
  };

  return (
    <div className="borrow-book-page">
      <div className="container borrow-book-container mt-5">
        <div className="row mt-4">
          <div className="d-flex">
            <div className="flex-item border rounded p-4 bg-light">
              <h2 className="text-primary">Find Book Details</h2>
              <div className='form-group mb-3'>
                <label htmlFor="bookId" className="text-dark">Book ID:</label>
                <input
                  type="number"
                  id="bookId"
                  className="form-control"
                  value={bookId}
                  onChange={(e) => setBookId(e.target.value)}
                  required
                />
              </div>
              <p className="text-success">Book Name: {bookName}</p>
              <p className="text-info">Author: {author}</p>
              <p className="text-warning">Quantity: {quantity}</p>
              <div className="text-center">
                <button type="button" className="btn btn-primary mt-3" onClick={getBookDetails}>
                  Find BOOK
                </button>
              </div>
            </div>

            <div className="flex-item border rounded p-4 bg-light shadow-lg ml-4">
              <h2 className="text-center text-primary">Borrow Book</h2>
              <form onSubmit={onSubmit}>
                <div className="form-group d-flex justify-content-between align-items-center mb-3">
                  <label htmlFor="book-id" className="col-form-label mr-2">Book Id:</label>
                  <input
                    type="number"
                    id="book-id"
                    className="form-control"
                    value={bookId}
                    onChange={(e) => setBookId(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group d-flex justify-content-between align-items-center mb-3">
                  <label htmlFor="borrow-date" className="col-form-label mr-2">Borrow Date:</label>
                  <input
                    type="date"
                    id="borrow-date"
                    className="form-control"
                    value={borrowDate}
                    onChange={handleBorrowDateChange}
                    required
                  />
                </div>
                <div className="form-group d-flex justify-content-between align-items-center mb-3">
                  <label htmlFor="due-date" className="col-form-label mr-2">Due Date:</label>
                  <input
                    type="date"
                    id="due-date"
                    className="form-control"
                    value={dueDate}
                    onChange={(e) => setDueDate(e.target.value)} // Allow manual override if needed
                    required
                  />
                </div>
                <div className="text-center">
                  <button type="submit" className="btn btn-success mt-3">
                    BORROW BOOK
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BorrowBook;
