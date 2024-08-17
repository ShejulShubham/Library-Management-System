import React, { useEffect, useState } from 'react';
import { getAllBooks } from '../service/bookService';
import BookList from '../components/book';
import { toast } from 'react-toastify';
import './BookList.css'; // Import custom CSS file for this page

function ListBook () {
    document.title = "MANAGE BOOKS";

    useEffect(() => {
        loadBooks();
    }, []);

    function handleError(error) {
        if (error.response) {
            console.error('Server responded with an error:', error.response.data);
            toast.error('Please try again later.');
        } else if (error.request) {
            console.error('No response received:', error.request);
            toast.error('No response from the server.');
        } else {
            console.error('Error setting up the request:', error.message);
        }
    }

    const emptyArr = [];

    const [bookDetail, setBookdetail] = useState([]);

    const loadBooks = async () => {
        try {
            const allBooks = await getAllBooks();
            console.log(JSON.stringify(allBooks));
            setBookdetail(allBooks);
        } catch (error) {
            handleError(error);
        }
    };

    return (
        <div className="list-book-page">
            <div className="content p-4 w-100">
                <div className="header d-flex justify-content-between align-items-center mb-4">
                    <h1>List of Books</h1>
                    <div className="d-grid gap-2"></div>
                </div>
                {Object.keys(bookDetail).length !== emptyArr.length ?
                    <table className="table table-striped table-bordered table-hover">
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
                                        key={book.bookId}
                                        id={book.bookId}
                                        title={book.bookTitle}
                                        category={book.category.categoryName}
                                        author={book.authors[0].authorName}
                                        quantity={book.quantity}
                                    />
                                );
                            })}
                        </tbody>
                    </table>
                    : <p>Could not load books</p>}
            </div>
        </div>
    );
}

export default ListBook;
