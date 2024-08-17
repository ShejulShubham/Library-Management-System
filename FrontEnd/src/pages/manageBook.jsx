import React, { useEffect, useState } from 'react';
import { addBook, deleteBookDetails, getAllBooks, getAllCategories, getBook, updateBookDetails } from '../service/bookService';
import { toast } from 'react-toastify';
import BookList from '../components/book';
//import { Container, Row, Col, Form, Button, Table } from 'react-bootstrap';

const ManageBooks = () => {
    //to set title of the page
    document.title = "MANAGE BOOKS";

    const [book, setBook] = useState({
      bookId: '',
      bookTitle: '',
      description: '',
      quantity: '',
      category: '',
      authors: {
        aId: '',
        authorName: '',
      },
    })

  const [bookId, setBookId] = useState('');
  const [bookName, setBookName] = useState('');
  const [authorName, setAuthorName] = useState([]);
  const [categoryName, setCategoryName] = useState([]);
  const [quantity, setQuantity] = useState('');
  const [description, setDescription] = useState('string');
  const[categoryId, setCategoryId] = useState(0);
  const[authorId, setAuthorId] = useState(0);

  const[categories, setCategories] = useState([]);

    // State to hold the selected category
    const [selectedCategory, setSelectedCategory] = useState('');


  useEffect(() => {
    getCategoryDetails();
    loadBooks();
  }, [])

  // Handler function when a category is selected
  const handleCategoryChange = (e) => {
    setSelectedCategory(e.target.value);
  };

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

  const getCategoryDetails = async() =>{
    // e.preventDefault();
    try{
      const data = await getAllCategories();
      // console.log(JSON.stringify(data));
      setCategories(data || []);
    }catch(error){
      handleError(error);
    }
  }


  const addBookDetails = async (e) => {
    e.preventDefault();
    // console.log(selectedCategory)
    
    const book = {
      title : bookName,
      categoryId,
      categoryName : selectedCategory,
      authorId,
      authorName: authorName,
      quantityAvailable : quantity,
      description,
    }

    // console.log(book)
    
    try{
      const result = await addBook(book);
  
      // console.log(JSON.stringify(result))
      await loadBooks();
      toast.success(result.message);

    }catch(error){
      handleError(error);
    }
    
  }

  const[bookDetail, setBookdetail] = useState([]);

  const loadBooks = async () => {


      try{
        const allBooks = await getAllBooks();
        
        // console.log(JSON.stringify(allBooks))
  
        setBookdetail(allBooks||[]);

      }catch(error){
        handleError(error);
      }
  }

  const findBookDetails = async (e) => {
    e.preventDefault();
    try{
        const data = await getBook(bookId);
        // console.log(JSON.stringify(data))

        // setBook(data);
        authorReturn(data);
    }catch(error){
      handleError(error);
    }

  }

  const handleUpdateBook = async (e) => {
    e.preventDefault();

    const data1 = {
      bookId: bookId, 
      bookTitle : bookName,
      categoryId,
      category : selectedCategory,
      authors: [{
        authorId,
        authorName: authorName}],
      quantity : quantity,
      description,
    }

    console.log(data1);


    try{
        const response = await updateBookDetails(data1);
        // console.log(JSON.stringify(data))
        await loadBooks();
        toast.success(response.message);


    }catch(error){
      handleError(error);
    }


  };

  // const [authors, setAuthors] = useState('');

  function authorReturn(data){
    setBookName(data.bookTitle);
    setAuthorName(data.authors.map(authors =>
      authors.authorName).join(', '));
    setSelectedCategory(data.category.categoryName);
    setQuantity(data.quantity);
  }

  const handleDeleteBook= async (e) => {
    e.preventDefault();
    try{
        const data = await deleteBookDetails(bookId);
        console.log(JSON.stringify(data));
        await loadBooks();
        toast.success(data.message);

    }catch(error){
      handleError(error);
    }
  }

  const emptyArr = [];

  return (
<div className="container">
  <div className="row">
    <div className="col-md-5">
      <div className="content p-4 w-100">
        {/* <a href="#" className="btn btn-danger mb-3">Back</a> */}
        <div className="mb-3">
          <label className="form-label">Enter Book Id</label>
          <input type="text" className="form-control" 
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
            required />
            <button className="btn btn-primary"
            onClick={findBookDetails}>Find Book Details</button>
        </div>
        <div className="mb-3">
          <label className="form-label">Enter Book Title</label>
          <input type="text" className="form-control" 
            value={bookName}
            onChange={(e) => setBookName(e.target.value)}
            required />
        </div>
        <div className="mb-3">
          <label className="form-label">Enter Author Name</label>
          <input type="text" className="form-control" 
          value={authorName}
          onChange={(e) => setAuthorName(e.target.value)}
          required />
        </div>
          <div>
              <label htmlFor="category-select">Choose a category:</label>
              <select 
                id="category-select" 
                className="form-select"
                value={selectedCategory} 
                onChange={handleCategoryChange}
              >
                <option value="">--Select a Category--</option>
                {/* {Object.keys(categories).length !== emptyArr.length ? */}
                {categories.map((category) => (
                  <option key={category.categoryId} value={category.categoryName}>
                    {category.categoryName}
                  </option>
                ))}
                {/* : categoryName} */}
              </select>

              {/* {selectedCategory && (
                <div className="mt-3">
                  <strong>Selected Category:</strong> {selectedCategory}
                </div>
              )} */}
          </div>
        <div className="mb-3">
          <label className="form-label">Enter Quantity</label>
          <input type="text" className="form-control"
           value={quantity}
           onChange={(e) => setQuantity(e.target.value)}
           required />
        </div>
        <div className="d-grid gap-2">
          <button className="btn btn-danger"
          onClick={addBookDetails}>ADD</button>
          <button className="btn btn-danger"
          onClick={handleUpdateBook}>UPDATE</button>
          <button className="btn btn-danger"
          onClick={handleDeleteBook}>DELETE</button>
        </div>
      </div>
    </div>

    <div className="col-md-7">
      <div className="content p-4 w-100">
        <div className="header d-flex justify-content-between align-items-center mb-4">
          <h4>Manage Books</h4>
          
        </div>
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Book Id</th>
              <th>Name</th>
              <th>Category</th>
              <th>Author</th>
              <th>Quantity</th>
            </tr>
          </thead>
          <tbody>
            {bookDetail.map((book) => {
              return (
                <BookList
                id = {book.bookId}
                title = {book.bookTitle}
                category = {book.category.categoryName}
                author = {book.authors.map(authors => authors.authorName).join(', ')}
                quantity = {book.quantity}
                />
              )
            })}
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <div className='row'><hr></hr><hr></hr></div>
</div>

         
  );
};

export default ManageBooks;
