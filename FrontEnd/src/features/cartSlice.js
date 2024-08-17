import { createSlice } from '@reduxjs/toolkit'

const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        items: [],
    },

    reducers: {
        addToCartAction: (state, action) => {
            // action.payload will receive the parameter passed while sending
            // the action with dispatch
            state.items.push(action.payload)
        },
        removeFromCartAction: (state, action) => {
            const property = action.payload
            for(let index = 0; index < state.items.length; index++){
                // search the property to be removed from the state 
                if(state.items[index.id === property.id]){
                    state.items.splice(index, 1)
                    break
                }
            }
        }
    },
})


export const { addToCartAction, removeFromCartAction} = cartSlice.actions
export default cartSlice.reducer