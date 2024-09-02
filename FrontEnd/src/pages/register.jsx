import React, { useState } from 'react';
import '../css/RegisterUser.css';
import { signup } from '../service/userService';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

const RegisterUser = () => {
    // Set title of the page
    document.title = "SIGN UP";

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: '',
        // role: '', // 
    });

    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // Client-side validations
            if (formData.firstName.length === 0) {
                toast.warning('Enter First Name');
            } else if (formData.lastName.length === 0) {
                toast.warning('Enter Last Name');
            } else if (formData.email.length === 0) {
                toast.warning('Enter Email');
            } else if (formData.password.length < 2) {
                toast.warning('Password must be at least 6 characters');
            } else if (formData.confirmPassword.length ===0) {
                toast.warning('Please enter confirm password');
            } else if (formData.password !== formData.confirmPassword) {
                toast.warning('Password does not match');
            } else {
                const result = await signup(formData);
                if (result['status'] === 'success') {
                    toast.success('Please login first');
                    navigate('/login');
                } else {
                    toast.error('Could not sign you up, please try again');
                }
            }
        } catch (error) {
            console.error('Registration failed:', error);
        }
    };

    return (
        <div className="register-page">
            <div className="container">
                <div className="row justify-content-center pt-5">
                    <div className="col-md-6 col-lg-5">
                        <div className="card form-container rounded shadow-sm p-3 mb-5">
                            <h2 className="text-center mb-3">SIGN UP</h2>

                            <form onSubmit={handleSubmit}>
                                <div className="form-group mb-2">
                                    <label htmlFor="firstName">First Name</label>
                                    <input
                                        type="text"
                                        id="firstName"
                                        name="firstName"
                                        className="form-control"
                                        value={formData.firstName}
                                        onChange={handleInputChange}
                                        required
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label htmlFor="lastName">Last Name</label>
                                    <input
                                        type="text"
                                        id="lastName"
                                        name="lastName"
                                        className="form-control"
                                        value={formData.lastName}
                                        onChange={handleInputChange}
                                        required
                                    />
                                </div>
{/* <div className="form-group mb-2">
    <label htmlFor="role">Role</label>
    <select
        id="role"
        name="role"
        className="form-control"
        value={formData.role}
        onChange={handleInputChange}
        required
    >
        <option value="ROLE_USER">User</option>
        <option value="ROLE_ADMIN">Admin</option>
    </select>
</div> */}
                                <div className="form-group mb-2">
                                    <label htmlFor="email">Your Email</label>
                                    <input
                                        type="email"
                                        id="email"
                                        name="email"
                                        className="form-control"
                                        value={formData.email}
                                        onChange={handleInputChange}
                                        required
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label htmlFor="password">Password</label>
                                    <input
                                        type="password"
                                        id="password"
                                        name="password"
                                        className="form-control"
                                        value={formData.password}
                                        onChange={handleInputChange}
                                        required
                                    />
                                </div>
                                <div className="mb-2">
                                    <label htmlFor="confirmPassword">Confirm Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="confirmPassword"
                                        name="confirmPassword"
                                        value={formData.confirmPassword}
                                        onChange={handleInputChange}
                                        required
                                    />
                                </div>
                                <div className="text-center">
                                    <button type="submit" className="btn btn-primary btn-lg">Register</button>
                                </div>
                            </form>
                        </div>

                        {/* Info below the registration block */}
                        <div className="info-text mt-4 text-muted text-center">
                            <p>
                                Signing up is free and easy! Just fill out the form to get started.
                            </p>
                            <p>
                                With a library card, you can borrow books, audiobooks, and ebooks. You can also access a variety of online resources, including research databases and streaming services.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterUser;
