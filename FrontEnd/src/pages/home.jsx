import React, { useEffect, useState } from 'react';
import './home.css';
import handleError from './utils';
import { getBookByCategory } from '../service/googleService';
import BookHomeList from '../components/bookHome';


// import userProfile from '../images/userProfile.jpg';
// import Cart from '../images/Cart.jpg';

function Home () {
  document.title = "Home";

  useEffect (() => {
    loadHome();
  }, []);

  const [category1, setCategory1] = useState([]);
  const [category2, setCategory2] = useState([]);
  const [category3, setCategory3] = useState([]);
  const [category4, setCategory4] = useState([]);

  const bookCategory1 = 'Fiction';
  const bookCategory2 = 'Biography';
  const bookCategory3 = 'History';
  const bookCategory4 = 'Literature';


  function shuffleList(list){
    return list.sort(() => 0.5 - Math.random());
  }

  
  const loadHome = async () => {

    try{
        const data1 = await getBookByCategory(bookCategory1);
        const data2 = await getBookByCategory(bookCategory2);
        const data3 = await getBookByCategory(bookCategory3);
        const data4 = await getBookByCategory(bookCategory4);


        setCategory1(data1.items);
        setCategory2(data2.items);
        setCategory3(data3.items);
        setCategory4(data4.items);

    }catch(error){
      handleError(error);
    }
  }

  return (
    <div className="home-container">
      <aside className="sidebar">
        {/* You can add other sidebar content here */}
      </aside>

      <main className="main-content">
        <header className="main-header">
          <h2>Book by Category</h2>
        </header>

        <div className="books-section">
          <div className="book-category">
            <h3>{bookCategory1}</h3>
            <div className="book-list">
              {category1?.slice(0, 4).map((book) => {
                return (
                  <BookHomeList
                    key = {book.id}
                    title = {book.volumeInfo.title}
                    authors = {book.volumeInfo.authors.join(", ")}
                    thumbnail = {book.volumeInfo.imageLinks?.thumbnail}
                    link = {book.selfLink}
                  />
                )
              })}
            </div>
          </div>

          <div className="book-category">
            <h3>{bookCategory2}</h3>
              <div className="book-list">
                {category2?.slice(0, 4).map((book) => {
                  return (
                    <BookHomeList
                      key = {book.id}
                      title = {book.volumeInfo.title}
                      authors = {book.volumeInfo.authors.join(", ")}
                      thumbnail = {book.volumeInfo.imageLinks?.thumbnail}
                      link = {book.selfLink}
                    />
                  )
                })}
              </div>
          </div>

          <div className="book-category">
            <h3>{bookCategory3}</h3>
              <div className="book-list">
                {category3?.slice(0, 4).map((book) => {
                  return (
                    <BookHomeList
                      key = {book.id}
                      title = {book.volumeInfo.title}
                      authors = {book.volumeInfo.authors.join(", ")}
                      thumbnail = {book.volumeInfo.imageLinks?.thumbnail}
                      link = {book.selfLink}
                    />
                  )
                })}
              </div>
          </div>

          <div className="book-category">
            <h3>{bookCategory4}</h3>
              <div className="book-list">
                {category4?.slice(0, 4).map((book) => {
                  return (
                    <BookHomeList
                      key = {book.id}
                      title = {book.volumeInfo.title}
                      authors = {book.volumeInfo.authors.join(", ")}
                      thumbnail = {book.volumeInfo.imageLinks?.thumbnail}
                      link = {book.selfLink}
                    />
                  )
                })}
              </div>
          </div>

        </div>
      </main>
    </div>
  );
}

export default Home;
