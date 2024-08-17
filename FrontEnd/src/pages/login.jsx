import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux'
import './LoginUser.css'; // Ensure this matches the correct path to your CSS file
import { signin } from "../service/userService";
import { toast } from 'react-toastify'
import { loginAction } from '../features/userSlice'

function LoginUser() {
    // to set title of the page
    document.title = "LOG IN";

    // create state members
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    // Add a state for role
const [role, setRole] = useState(''); // Default to user role

    // get the navigate object
    const navigate = useNavigate()

    // use dispatch to update global state
    const dispatch = useDispatch()

  const onLogin = async (e) => {
    e.preventDefault()

    console.log('Email:', email, 'Password:', password);

    // client side validations
    if(email.length === 0){
      toast.warning('Enter Email')
    }else if(password.length === 0){
      toast.password('Enter Password')
    }else{
      // get user details from email and password
      await signin(email, password)
      .then(response => {
        if(response['status'] === 'success'){

          const id = response['id']
          const firstName = response['firstName']
          const lastName = response['lastName']
          const email = response['email']
          const role = response['role']

          localStorage.setItem('userId', id)
          localStorage.setItem('email', email)
          localStorage.setItem('role', role)
          localStorage.setItem('firstName', firstName)
          localStorage.setItem('lastName', lastName)

        // set the login status to true
          dispatch(loginAction())
          
        // show message
        toast.success(`${firstName} ${lastName},
        Welcome to the Discovery LMS!`)

        toast.success("Click on More to explore options")
      
        if(role === 'ROLE_ADMIN')
          navigate('/dashboard')
        else// navigate to home
          navigate('/home')
        
      }
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
    });

      // sample response
      // {
      //   "id": 5,
      //   "firstName": "raj",
      //   "lastName": "saytode",
      //   "email": "raj@gmail.com",
      //   "role": "ROLE_USER",
      //   "status": "success"
      // }

      // console.log(JSON.stringify(result))

      

      // }else {
      //   toast.error(result['message'])
      //   navigate('/login')
      // }
    }

  }

  return (
    <div className="container-fluid d-flex flex-column h-100 login-page">
      <main className="d-flex justify-content-center align-items-center flex-grow-1">
        <div className="col-xs-12 col-sm-8 col-md-6 col-lg-4 col-xl-3 card p-4">
          <h1 className="text-center mb-4">Login</h1>
          <form onSubmit={onLogin}>
            <div className="form-group mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input
                type="text"
                className="form-control"
                id="email"
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <div className="form-group mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
           
{/* <div className="form-group mb-3">
  <label htmlFor="role" className="form-label">Role</label>
  <select
    className="form-control"
    id="role"
    name="role"
    value={role}
    onChange={(e) => setRole(e.target.value)}
    required
  >
    <option value="ROLE_USER">User</option>
    <option value="ROLE_ADMIN">Admin</option>
  </select>
</div> */}
            <div className="form-check mb-3">
              <input type="checkbox" className="form-check-input" id="rememberMe" />
              <label className="form-check-label" htmlFor="rememberMe">Remember Me</label>
            </div>
            <button type="submit" className="btn btn-primary w-100">Login</button>
            {/* <Link to="#" className="forgot-password-link">Forgot Password?</Link> */}
            <p className="text-center mt-4">Don't have an account? <Link to="/register">Register Here</Link></p>
          </form>
        </div>
      </main>
      <footer className="footer">
        <div className="container text-center">
          <span className="text-muted"></span>
        </div>
      </footer>
    </div>
  )
}



export default LoginUser;