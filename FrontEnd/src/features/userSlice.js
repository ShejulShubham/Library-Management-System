import { createSlice } from '@reduxjs/toolkit'

const initialValue = {
    //initialize from local storage
    isLoggedIn: !!localStorage.getItem('isLoggedIn'),

}


const userSlice = createSlice({
    name: 'user',
    initialState: initialValue, 
    reducers: {
        loginAction: (state) => {
            state.isLoggedIn = true


            localStorage.setItem('isLoggedIn', true);
        },

        logoutAction: (state) => {
            state.isLoggedIn = false

            localStorage.removeItem('isLoggedIn');
        },
    },
})

export const {loginAction, logoutAction} = userSlice.actions
export default userSlice.reducer
