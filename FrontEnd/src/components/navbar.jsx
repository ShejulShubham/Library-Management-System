import logo from '../images/LMS_logo.jpg';
// import '../App.css';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import './nav.css';
import { useDispatch } from 'react-redux';
import { logoutAction } from '../features/userSlice';


function MyLogo() {
  return (
    // <div className="logo-container">
    //   <img src={logo} alt="Your Company Logo" className="logo" />
    // </div>
      // <!-- Image and text -->
    <div className="logo-container">
      <nav className="navbar navbar-light bg-light">
        {/* <a class="navbar-brand">
          <img src={logo} width="30" height="30" class="d-inline-block align-top" alt="Logo"/>
        </a> */}
        <img
        src={logo}
        className="me-2"
        height="40"
        alt="Logo"
        loading="lazy"
      />
      </nav>
    </div>

    
  );
}


function LogInButton(){
  return (
    <li className="nav-item">
    <Link className="nav-link" to="/login">
      <button data-mdb-ripple-init type="button" className="btn btn-primary me-2">
          Log In
        </button>
      </Link>
    </li>
  )
}

function SignUpButton(){
  return (
    <li className="nav-item">
    <Link className="nav-link" to="/register">
    <button data-mdb-ripple-init type="button" className="btn btn-primary me-2">
        Sign Up
      </button>
    </Link>
  </li>
  )
}



export const Layout = () => {
  //  return (
      //give pathname for different navbar and footer
  
      const renderNavbar = () => {
          switch (localStorage.getItem('role')) {
            case 'ROLE_ADMIN':
              return <AdminNavBar />;
            default:
              return <NavbarAfterLogIn />;
          }
        };
      
        return <>{renderNavbar()}</>;
      
      // <div>
      //     {location.pathname === '/login' ? <MyNavbar1 /> : <MyNavbar/>}
      //     {children}
      //     {location.pathname=='/login' ? <MyFooter/> : <MyFooter/>}
      // </div>

  //  );

};


function User() {


  const value = localStorage.getItem('firstName');



  return (
    <li className="nav-item">
    <Link className="nav-link active" to="#">
    <button data-mdb-ripple-init type="button" className="btn">
        <p>Hi! {value} </p>
      </button>
    </Link>
  </li>

  )
}



// const Navbar = () => {
//   const location = useLocation();

//   const renderNavbar = () => {
//     switch(location.pathname){
//       case'/home':
//       return <MyNavbar />;

//       default:
//         return <MyNavbar1 />;
//     }

//   }
  
//   return  <>{renderNavbar()}</>;

// };

export const NavbarAfterLogIn = () =>{

  const navigate = useNavigate();

  const dispatch = useDispatch();


  const onLogout = () => {
    // clear the local storage
    localStorage.removeItem('userId')
    localStorage.removeItem('firstName')
    localStorage.removeItem('lastName')
    localStorage.removeItem('email')
    localStorage.removeItem('role')


    // set the login status to false
    dispatch(logoutAction())

    // navigate to login page
    navigate('/login')
  }


  return (
    <header className="d-flex flex-wrap w-100 justify-content-between align-itms-center border-bottom">
        <div className="d-flex align-items-center col-md-4">
          <MyLogo />
          <Link className='nav-link' to="/home">Discovery LMS</Link>
        </div>

        <ul className="nav col-md-6 justify-content-end list-unstyled">
          {/* Current page - Home (highlighted) */}
          <li className="nav-item">
            <Link className="nav-link active" to="/home">
            <button data-mdb-ripple-init type="button" className="btn">
                Home
              </button>
            </Link>
          </li>

          {/* Dropdown Menu */}
          <li className="nav-item dropdown">
            <a
              className="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <button data-mdb-ripple-init type="button" className="btn">
                More
              </button>
            </a>
            <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><Link className="dropdown-item" to="/borrow">Borrow Book</Link></li>
              <li><Link className="dropdown-item" to="/return">Return Book</Link></li>
              <li><Link className="dropdown-item" to="/booklist">Book List</Link></li>

            </ul>
          </li>

          {/* User Button */}
            <User/>

          {/* Login and Signup at right corner */}
          <li className="nav-item">
          <Link className="nav-link" to="/login">
            <button data-mdb-ripple-init type="button" className="btn btn-primary me-2"
            onClick={onLogout}>
                Log out
              </button>
            </Link>
          </li>
        </ul>
    </header>

  );
}

export const NavbarBeforeLogin = () => {
  const location = useLocation();
  return (
<div className="d-flex flex-wrap w-100 justify-content-between align-itms-center border-bottom">
  <div className="d-flex align-items-center">
    <MyLogo />
    <Link className='nav-link' to="/home">Discovery LMS</Link>
  </div>

  <ul className="nav col-md-6 justify-content-end list-unstyled">
    {/* Current page - Home (highlighted) */}
    <li className="nav-item">
      <Link className="nav-link active" to="/home">
      <button data-mdb-ripple-init type="button" className="btn">
          Home
        </button>
      </Link>
    </li>

    {/* Dropdown Menu */}
    {/* <li className="nav-item dropdown">
      <a
        className="nav-link dropdown-toggle"
        href="#"
        id="navbarDropdown"
        role="button"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      >
        <button data-mdb-ripple-init type="button" className="btn">
          More
        </button>
      </a>
      <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
        <li><Link className="dropdown-item" to="/return">Return Book</Link></li>
        <li><Link className="dropdown-item" to="/manageuser">Manage Users</Link></li>
        <li><Link className="dropdown-item" to="/managebook">Manage Books</Link></li>
        <li><Link className="dropdown-item" to="/viewbook">View Records</Link></li>
        <li><Link className="dropdown-item" to="/borrow">Borrow Book</Link></li>
        <li><Link className="dropdown-item" to="/defaulter">Defaulters List</Link></li>
      </ul>
    </li> */}

    <li className="nav-item">
      <Link className="nav-link active" to="/about">
      <button data-mdb-ripple-init type="button" className="btn">
          About
        </button>
      </Link>
    </li>
    {location.pathname === '/login' ?  <SignUpButton/> : <LogInButton/>}
  </ul>
</div>

  );
}


const AdminNavBar = () => {

  const navigate = useNavigate();

  const dispatch = useDispatch();


  const onLogout = () => {
    // clear the local storage
    localStorage.removeItem('userId')
    localStorage.removeItem('firstName')
    localStorage.removeItem('lastName')
    localStorage.removeItem('email')
    localStorage.removeItem('role')


    // set the login status to false
    dispatch(logoutAction())

    // navigate to login page
    navigate('/login')
  }

  return (
    <header className="d-flex flex-wrap w-100 justify-content-between align-itms-center border-bottom">
        <div className="d-flex align-items-center col-md-4">
          <MyLogo />
          <Link className='nav-link' to="/home">Discovery LMS</Link>
        </div>

        <ul className="nav col-md-6 justify-content-end list-unstyled">
          {/* Current page - Home (highlighted) */}
          <li className="nav-item">
            <Link className="nav-link active" to="/home">
            <button data-mdb-ripple-init type="button" className="btn">
                Home
              </button>
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link active" to="/dashboard">
            <button data-mdb-ripple-init type="button" className="btn">
                Dashboard
              </button>
            </Link>
          </li>

          {/* Dropdown Menu */}
          <li className="nav-item dropdown">
            <a
              className="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <button data-mdb-ripple-init type="button" className="btn">
                More
              </button>
            </a>
            <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><Link className="dropdown-item" to="/managebook">Manage Books</Link></li>
            <li><Link className="dropdown-item" to="/booklist">View All Books</Link></li>
            <li><Link className="dropdown-item" to="/manageuser">View All Users</Link></li>
            <li><Link className="dropdown-item" to="/viewrecords">View Borrow Records</Link></li>
            <li><Link className="dropdown-item" to="/defaulter">Defaulter List</Link></li>

            </ul>
          </li>

          <li className="nav-item">
          <Link className="nav-link" to="/login">
            <button data-mdb-ripple-init type="button" className="btn btn-primary me-2"
            onClick={onLogout}>
                Log out
              </button>
            </Link>
          </li>
        </ul>
      </header>
  );
}


export const MyFooter = () => {
  return (
    <footer>
      <p className="mb-0 text-muted text-center">2024 Library Management System</p>
    </footer>
  );


}

