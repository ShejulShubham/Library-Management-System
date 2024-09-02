import React, { useEffect, useState } from 'react';
import '../css/home.css';
import handleError from './utils';
import { getBookByCategory } from '../service/googleService';
import BookHomeList from '../components/bookHome';


function Home () {
  document.title = "Home";

  useEffect (() => {
    loadHome();
  });


  const bookCategories = ['Fiction', 'Biography', 'History', 'Literature', 'Science'];
  const [categoryData, setCategoryData] = useState({});


  function shuffleList(list){
    return list.sort(() => 0.5 - Math.random());
  }

  
  const loadHome = async () => {

    try{
        const data = {};  

        for(var i = 0; i < bookCategories.length; i++){
          const category = bookCategories[i];
          const result  = await getBookByCategory(category);

          data[category] = result.items;
        }

        setCategoryData(data);

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
          <h2>Books by Category</h2>
        </header>

        <div className="books-section">
          {bookCategories?.map((category) =>{
            return (
              <div className="book-category" key={category}>
              <h3>{category}</h3>
                      <BookHomeList data = {categoryData[category]}/>
              </div>  
            )
          })}
        </div>
      </main>
    </div>
  );
}

export default Home;
