import { useSelector } from 'react-redux'
import { Route, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import LoginUser from './pages/login';  
import RegisterUser from './pages/register';
import Home from './pages/home';
import AboutPage from './pages/about';
import ManageBooks from './pages/manageBook';
import ReturnBook from './pages/returnBook';
import ViewRecords from './pages/viewRecords';
import DefaulterList from './pages/defaulterList';
import ManageUsers from './pages/manageUser';
import BorrowBook from './pages/borrowBook';
import UserProfile from './pages/userprofile';
import UpdatePassword from './pages/updatepassword';
import { Layout, MyFooter, NavbarBeforeLogin } from './components/navbar';
import Dashboard from './pages/dashboard';
import ListBook from './pages/bookList';


function App() {
  const user = useSelector((state) => state.user)

  return (
    
    <div className="App">
      { !user.isLoggedIn ? <NavbarBeforeLogin/> : <Layout/>}
      
      <Routes>
        
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<LoginUser />} />
        <Route path='/register' element={<RegisterUser />} />
        <Route path='/home' element={<Home />} />
        <Route path='/return' element={<ReturnBook/>}/>
        <Route path='/about' element={<AboutPage />} />
        <Route path='/manageuser' element={<ManageUsers />} />
        <Route
                    path='/manageBook'
                    element={
                        
                            <ManageBooks />
                        
                    }
                />
        <Route path='/viewrecords' element={<ViewRecords />} />
        <Route path='/defaulter' element={<DefaulterList/>}/>
        <Route path='/borrow' element={<BorrowBook/>}/>
        <Route path='/userprofile' element={<UserProfile/>}/>
        <Route path='/updatepassword' element={<UpdatePassword/>}/>
        <Route path='/dashboard' element={<Dashboard/>}/>
        <Route path='/booklist' element={<ListBook/>}/>

      </Routes>
      

      <MyFooter/>
      <ToastContainer />
    </div>
  );
}

export default App;
