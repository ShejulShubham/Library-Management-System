import React, { useEffect, useState } from 'react';
import '../css/home.css';
import handleError from './utils';
import { getBookByCategory } from '../service/googleService';
import BookHomeList from '../components/bookHome';
import ProgressBar from '../components/progressBar';



function Home () {
  document.title = "Home";

  useEffect (() => {
    loadHome();
  }, []);// Added empty dependency array to run only on mount


  const bookCategories = ['Fiction', 'Biography', 'History', 'Literature', 'Science'];
  const [categoryData, setCategoryData] = useState({});
  const [loading, setLoading] = useState(true);
  const [progress, setProgress] = useState(0);


  function shuffleList(list){
    return list.sort(() => 0.5 - Math.random());
  }

  
  const loadHome = async () => {

    setLoading(true); //loading is set true before fetching data
    setProgress(10); //progress i set to 10%

    try{
        const data = {};  

        for(var i = 0; i < bookCategories.length; i++){
          const category = bookCategories[i];
          const result  = await getBookByCategory(category);

          data[category] = result.items;

          setProgress((preProgress) => preProgress + (90 / bookCategories.length));
        }

        setCategoryData(data);

    }catch(error){
      handleError(error);
    }finally{
      setLoading(false);
      setProgress(100);
    }
  }

  return (
    <div className="home-container">
      {
        <ProgressBar
          progress = {progress}
        />
      }
      <aside className="sidebar">
        {/* You can add other sidebar content here */}
      </aside>

      <main className="main-content">
        <header className="main-header">
          <h2>Books by Category</h2>
        </header>
        {loading? (
          <div className='loading'>Loading Content.....</div>
        ) : (
          <div className="books-section">
          {bookCategories.map((category) =>{
            return (
              <div className="book-category" key={category}>
              <h3>{category}</h3>
                      <BookHomeList data = {categoryData[category]}/>
              </div>  
            )
          })}
        </div>

        )}
      </main>
    </div>
  );
}

export default Home;
