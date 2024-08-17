import React from 'react';
import './home.css'; // Import the CSS file for styling
import Book01 from '../images/Book01.jpg';
import Book02 from '../images/Book02.jpg';
import Book03 from '../images/Book03.jpg';
import Book04 from '../images/Book04.jpg';
import newFiction1 from '../images/newFiction1.jpg';
import newFiction2 from '../images/newFiction2.jpg';


import comedy1 from '../images/comedy1.jpg';
import comedy2 from '../images/comedy2.jpg';
import comedy3 from '../images/comedy3.jpg';
import comedy4 from '../images/comedy4.jpg';
import comedy5 from '../images/comedy5.jpg';
import comedy6 from '../images/comedy6.jpg';

// Thriller books
import thriller1 from '../images/thriller1.jpg';
import thriller2 from '../images/thriller2.jpg';
import thriller3 from '../images/thriller3.jpg';
import thriller4 from '../images/thriller4.jpg';
import thriller5 from '../images/thriller5.jpg';
import thriller6 from '../images/thriller6.jpg';

import nonfiction1  from '../images/nonfiction1.jpg';
import nonfiction2  from '../images/nonfiction2.jpg';
import nonfiction3  from '../images/nonfiction3.jpg';
import nonfiction4  from '../images/nonfiction4.jpg';
import nonfiction5  from '../images/nonfiction5.jpg';
import nonfiction6  from '../images/nonfiction6.jpg';


import userProfile from '../images/userProfile.jpg';
import Cart from '../images/Cart.jpg';

function Home () {
  document.title = "Home";

  return (
    <div className="home-container">
      <aside className="sidebar">
        {/* You can add other sidebar content here */}
      </aside>

      <main className="main-content">
        <header className="main-header">
          <h2>My Books</h2>
        </header>

        <div className="books-section">


      

        <div className="book-category">
  <h3>Fiction</h3>
  <div className="book-list">
    <div className="book-item">
      <img src={Book01} alt="Harry Potter book cover" />
      <p>Harry Potter</p>
      <span>by J.K. Rowling</span>
      <button className="add-to-cart-button">View</button>
    </div>

    <div className="book-item">
      <img src={Book02} alt="The Past is Rising book cover" />
      <p>The Past is Rising</p>
      <span>by Kathryn Bywaters</span>
      <button className="add-to-cart-button">View</button>
    </div>

    <div className="book-item">
      <img src={Book03} alt="The Last Four Things book cover" />
      <p>The Last Four Things</p>
      <span>by Paul Hoffman</span>
      <button className="add-to-cart-button">View</button>
    </div>

    <div className="book-item">
      <img src={Book04} alt="Other London book cover" />
      <p>Other London</p>
      <span>by M.V. Stott</span>
      <button className="add-to-cart-button">View</button>
    </div>

    <div className="book-item">
      <img src={newFiction1} alt="The Great Escape book cover" />
      <p>The Great Escape</p>
      <span>by Richard Flanagan</span>
      <button className="add-to-cart-button">View</button>
    </div>

    <div className="book-item">
      <img src={newFiction2} alt="The Night Circus book cover" />
      <p>The Night Circus</p>
      <span>by Erin Morgenstern</span>
      <button className="add-to-cart-button">View</button>
    </div>
  </div>
</div>

          <div className="book-category">
            <h3>Comedy</h3>
            <div className="book-list">
              <div className="book-item">
                <img src={comedy1} alt="Comedy Book Cover" />
                <p>Albert Brooks</p>
                <span>Twenty Thirsty</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={comedy2} alt="Comedy Book Cover" />
                <p>Dangerously Funny</p>
                <span>by David</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={comedy3} alt="Comedy Book Cover" />
                <p>The Kids in the Hall</p>
                <span>by Paul Myers</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={comedy4} alt="Comedy Book Cover" />
                <p>Girls Walk into a Bar</p>
                <span>by Rachel Dratch</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={comedy5} alt="Comedy Book Cover" />
                <p>Lose Well</p>
                <span>by Chris Gethard</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={comedy6} alt="Comedy Book Cover" />
                <p>How to Be Alone</p>
                <span>by Lane Moore</span>
                <button className="add-to-cart-button">View</button>
              </div>
            </div>
          </div>

          {/* Non-Fiction Category */}


          {/* New Thriller Category */}
          <div className="book-category">
            <h3>Thriller</h3>
            <div className="book-list">
              <div className="book-item">
                <img src={thriller1} alt="The Girl with the Dragon Tattoo book cover" />
                <p>The Girl Dragon Tattoo</p>
                <span>by Stieg Larsson</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={thriller2} alt="Gone Girl book cover" />
                <p>Gone Girl</p>
                <span>by Gillian Flynn</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={thriller3} alt="The Silent Patient book cover" />
                <p>The Silent Patient</p>
                <span>by Alex Michaelides</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={thriller4} alt="Sharp Objects book cover" />
                <p>Sharp Objects</p>
                <span>by Gillian Flynn</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={thriller5} alt="The Woman in the Window book cover" />
                <p>The Woman in  Window</p>
                <span>by A.J. Finn</span>
                <button className="add-to-cart-button">View</button>
              </div>

              <div className="book-item">
                <img src={thriller6} alt="The Girl on the Train book cover" />
                <p>The Girl on the Train</p>
                <span>by Paula Hawkins</span>
                <button className="add-to-cart-button">View</button>
              </div>
            </div>
          </div>

          <div className="book-category">
  <h3>Non-Fiction</h3>
  <div className="book-list">
    <div className="book-item">
      <img src={nonfiction1} alt="Sapiens book cover" />
      <p>Sapiens</p>
      <span>by Yuval Noah Harari</span>
      <button className="add-to-cart-button">View</button>
    </div>
    <div className="book-item">
      <img src={nonfiction2} alt="Educated book cover" />
      <p>Educated</p>
      <span>by Tara Westover</span>
      <button className="add-to-cart-button">View</button>
    </div>
    <div className="book-item">
      <img src={nonfiction3} alt="Becoming book cover" />
      <p>Becoming</p>
      <span>by Michelle Obama</span>
      <button className="add-to-cart-button">View</button>
    </div>
    <div className="book-item">
      <img src={nonfiction4} alt="The Power of Habit book cover" />
      <p>The Power of Habit</p>
      <span>by Charles Duhigg</span>
      <button className="add-to-cart-button">View</button>
    </div>
    <div className="book-item">
      <img src={nonfiction5} alt="The Wright Brothers book cover" />
      <p>The Wright Brothers</p>
      <span>by David McCullough</span>
      <button className="add-to-cart-button">View</button>
    </div>
    <div className="book-item">
      <img src={nonfiction6} alt="Outliers book cover" />
      <p>Outliers</p>
      <span>by Malcolm Gladwell</span>
      <button className="add-to-cart-button">View</button>
    </div>
  </div>
</div>

        </div>
      </main>
    </div>
  );
}

export default Home;
